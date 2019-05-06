package com.example.win.easy;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.win.easy.db.PlayerJoinMouse;
import com.example.win.easy.db.PlayerJoinMouseDao;
import com.example.win.easy.db.dao.PlayerPojoDao;
import com.example.win.easy.db.pojo.MousePojo;
import com.example.win.easy.db.dao.MousePojoDao;
import com.example.win.easy.db.dao.ParameterDAO;
import com.example.win.easy.db.ThisDatabase;
import com.example.win.easy.db.Parameter;
import com.example.win.easy.db.pojo.PlayerPojo;
import com.example.win.easy.song.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SimpleDatabaseTest {
    private ThisDatabase thisDatabase;
    private MousePojoDao mousePojoDao;
    private PlayerPojoDao playerPojoDao;
    private ParameterDAO parameterDAO;
    private PlayerJoinMouseDao playerJoinMouseDao;
    private MousePojo mousePojo1 =new MousePojo();
    private MousePojo mousePojo2 =new MousePojo();
    private Parameter parameter1=new Parameter();
    private Parameter parameter2=new Parameter();
    private PlayerPojo playerPojo1=new PlayerPojo();
    private PlayerPojo playerPojo2=new PlayerPojo();
    private List<PlayerJoinMouse> joinList=new ArrayList<>();


    @Before
    public void createDb(){
        Context context= ApplicationProvider.getApplicationContext();
        thisDatabase = Room.inMemoryDatabaseBuilder(context, ThisDatabase.class).build();
        mousePojoDao = thisDatabase.mousePojoDao();
        parameterDAO= thisDatabase.parameterDAO();
        playerPojoDao=thisDatabase.playerPojoDao();
        playerJoinMouseDao=thisDatabase.playerJoinMouseDao();
    }

    @After
    public void closeDb(){
        thisDatabase.close();
    }

    @Test
    public void testReadWrite(){

        createParameter();
        createMousePojo();
        createPlayerPojo();

        Long pid1=parameterDAO.insert(parameter1);
        Long pid2=parameterDAO.insert(parameter2);

        mousePojo1.setParamId(pid1);
        mousePojo2.setParamId(pid2);

        Long mid1= mousePojoDao.insert(mousePojo1);
        Long mid2= mousePojoDao.insert(mousePojo2);

        Long plid1=playerPojoDao.insert(playerPojo1);
        Long plid2=playerPojoDao.insert(playerPojo2);

        joinList.add(new PlayerJoinMouse(plid1,mid1));
        joinList.add(new PlayerJoinMouse(plid1,mid2));
        joinList.add(new PlayerJoinMouse(plid2,mid1));
        joinList.add(new PlayerJoinMouse(plid2,mid2));

        for(PlayerJoinMouse playerJoinMouse:joinList)
            playerJoinMouseDao.insert(playerJoinMouse);

//        List<MousePojo> mousePojoList = mousePojoDao.findAllMouses();
//        for (MousePojo mousePojo : mousePojoList){
//            Parameter parameter=parameterDAO.findById(mousePojo.getParamId());
//            System.out.println(mousePojo +" :: "+parameter);
//        }
        System.out.println(playerJoinMouseDao.findAllMousePojosForPlayerById(plid1));
        System.out.println(playerJoinMouseDao.findAllMousePojosForPlayerById(plid2));
        System.out.println(playerJoinMouseDao.findAllPlayerPojosForMouseById(mid1));
        System.out.println(playerJoinMouseDao.findAllPlayerPojosForMouseById(mid2));

    }

    private void createParameter(){
        parameter1.setSize(233);
        parameter1.setWeight(2333);
        parameter2.setSize(23333);
        parameter2.setWeight(233333);
    }

    private void createMousePojo(){
        List<Character> sequence1=new ArrayList<>();
        sequence1.add('G');
        sequence1.add('5');
        sequence1.add('0');
        sequence1.add('2');
        mousePojo1.setBand("罗技");
        mousePojo1.setVersion(502);
        mousePojo1.setSequence(sequence1);
        mousePojo1.setDataSource(DataSource.Local);


        List<Character> sequence2=new ArrayList<>();
        sequence2.add('M');
        sequence2.add('5');
        sequence2.add('9');
        sequence2.add('0');
        mousePojo2.setBand("Logitech");
        mousePojo2.setVersion(590);
        mousePojo2.setSequence(null);
        mousePojo2.setDataSource(null);
    }

    private void createPlayerPojo(){
        playerPojo1.setName("Gali");
        playerPojo2.setName("Mag");
    }


}
