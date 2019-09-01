package com.example.win.easy.download;

import android.os.Environment;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.win.easy.enumeration.DataSource;
import com.example.win.easy.repository.db.data_object.SongDO;
import com.example.win.easy.repository.db.data_object.SongListDO;

import org.apache.commons.io.FileExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ResultOfMethodCallIgnored")
@RunWith(AndroidJUnit4ClassRunner.class)
public class FileServiceTest {

    private FileService fileService=new FileService();



    /**
     * <p>测试service提供的各个方法可以正确生成文件，这个所谓的“正确生成文件”包括：</p>
     * <ol>
     *     <li>生成的文件名是正确的（与预期相符合）</li>
     *     <li>返回的文件确确实实创建了，而不是只new了个File对象</li>
     * </ol>
     */
    @Test
    public void testFileCorrectCreation() throws FileAlreadyExistsException {
        cleanUpFileEnvironment();

        //获取实际调用结果
        File actualSongFile=fileService.file(testSong);
        File actualSongAvatarFile=fileService.avatar(testSong);
        File actualSongListAvatarFile=fileService.avatar(testSongList);

        //首先确保路径正确
        assertEquals(expectedSongFile.getAbsolutePath(),actualSongFile.getAbsolutePath());
        assertEquals(expectedSongAvatarFile.getAbsolutePath(),actualSongAvatarFile.getAbsolutePath());
        assertEquals(expectedSongListAvatarFile.getAbsolutePath(),actualSongListAvatarFile.getAbsolutePath());

        //而后确保真的创建了，在文件系统中存在
        assertTrue(expectedSongFile.exists());
        assertTrue(expectedSongAvatarFile.exists());
        assertTrue(expectedSongListAvatarFile.exists());

        //删文件，收尾
        expectedSongFile.delete();
        expectedSongAvatarFile.delete();
        expectedSongListAvatarFile.delete();
    }

    /**
     * <p>测试当要测试的文件已经存在时，应当报异常</p>
     * <p>（这个Test没办法收尾，这也是为什么在正常的测试之前需要清空文件。为了避免这个测试先执行）</p>
     */
    @Test(expected = FileExistsException.class)
    public void testWhenFileAlreadyExists() throws IOException {
        setUpSongFileAlreadyExistsCondition();
        fileService.file(testSong);
    }

    @Before
    public void setUp() {
        String uid="5456";

        //设置歌曲信息
        String songName="";

        String songUrl="https://www.google.com/songs/kyu5j6hg5hgj156t.mp3";
        String songExtension=".mp3";

        String songAvatarUrlPrefix="https://www.google.com/pictures/";
        String songAvatarUri="fds5sd456d4sfd16.jpg";
        String songAvatarUrl=songAvatarUrlPrefix+songAvatarUri;

        //设置歌单信息
        DataSource songListSource=DataSource.WangYiYun;
        String songListAvatarUrlPrefix="https://www.google.com/pictures/";
        String songListAvatarUri="a489wee4eh5fg1h.jpg";
        String songListAvatarUrl=songListAvatarUrlPrefix+songListAvatarUri;

        //生成歌曲和歌单对象
        testSong =SongDO.builder().uid(uid).name(songName).songUrl(songUrl).avatarUrl(songAvatarUrl).build();
        testSongList = SongListDO.builder().source(songListSource).avatarUrl(songListAvatarUrl).build();

        //设置期望得到的文件们的路径
        String rootDir=Environment.getExternalStorageDirectory().getAbsolutePath();
        String songDir="songs/";
        String songListDir="songLists/";
        String pictureDir="pictures/";

        //{rootDir}/{songDir}/{uid}/{songFilename}{songExtension}
        expectedSongFile=new File(rootDir+songDir+uid+"/"+songName+songExtension);

        //{rootDir}/{pictureDir}/{songDir}/{uid}/{pictureWebUri}
        expectedSongAvatarFile=new File(rootDir+pictureDir+songDir+uid+"/"+songAvatarUri);

        //{rootDir}/{pictureDir}/{songListDir}/{dataSource}/{uid}/{pictureWebUri}
        expectedSongListAvatarFile=new File(rootDir+pictureDir+songListDir+songListSource.toString()+"/"+uid+"/"+songListAvatarUri);
    }

    private void setUpSongFileAlreadyExistsCondition() throws IOException {
//        Log.d("File",expectedSongFile.getAbsolutePath());
//        if (!expectedSongFile.getParentFile().exists())
//            assertTrue(expectedSongFile.mkdirs());
        expectedSongFile.createNewFile();
        assertTrue(expectedSongFile.exists());
    }

    private void cleanUpFileEnvironment(){
        if (expectedSongFile.exists())
            expectedSongFile.delete();
        assertFalse(expectedSongFile.exists());
    }

    private SongDO testSong;
    private SongListDO testSongList;

    private File expectedSongFile;
    private File expectedSongAvatarFile;
    private File expectedSongListAvatarFile;

}