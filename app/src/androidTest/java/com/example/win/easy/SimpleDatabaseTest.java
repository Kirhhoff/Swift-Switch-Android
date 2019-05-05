package com.example.win.easy;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.win.easy.domain.Mouse;
import com.example.win.easy.domain.MouseDao;
import com.example.win.easy.domain.ParameterDAO;
import com.example.win.easy.domain.ThisDatabase;
import com.example.win.easy.domain.Parameter;
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
    private MouseDao mouseDao;
    private ParameterDAO parameterDAO;
    private Mouse mouse1=new Mouse();
    private Mouse mouse2=new Mouse();
    private Parameter parameter1=new Parameter();
    private Parameter parameter2=new Parameter();

    @Before
    public void createDb(){
        Context context= ApplicationProvider.getApplicationContext();
        thisDatabase = Room.inMemoryDatabaseBuilder(context, ThisDatabase.class).build();
        mouseDao= thisDatabase.mouseDao();
        parameterDAO= thisDatabase.parameterDAO();
    }

    @After
    public void closeDb(){
        thisDatabase.close();
    }

    @Test
    public void testReadWrite(){

        createParameter();
        createMouse();

        Long pid1=parameterDAO.insert(parameter1);
        Long pid2=parameterDAO.insert(parameter2);

        mouse1.setParamId(pid1);
        mouse2.setParamId(pid2);

        Long mid1=mouseDao.insert(mouse1);
        Long mid2=mouseDao.insert(mouse2);

        List<Mouse> mouseList=mouseDao.findAllMouses();
        for (Mouse mouse:mouseList){
            Parameter parameter=parameterDAO.findById(mouse.getParamId());
            System.out.println(mouse+" :: "+parameter);
        }
    }

    private void createParameter(){
        parameter1.setSize(233);
        parameter1.setWeight(2333);
        parameter2.setSize(23333);
        parameter2.setWeight(233333);
    }

    private void createMouse(){
        List<Character> sequence1=new ArrayList<>();
        sequence1.add('G');
        sequence1.add('5');
        sequence1.add('0');
        sequence1.add('2');
        mouse1.setBand("罗技");
        mouse1.setVersion(502);
        mouse1.setSequence(sequence1);
        mouse1.setDataSource(DataSource.Local);


        List<Character> sequence2=new ArrayList<>();
        sequence2.add('M');
        sequence2.add('5');
        sequence2.add('9');
        sequence2.add('0');
        mouse2.setBand("Logitech");
        mouse2.setVersion(590);
        mouse2.setSequence(null);
        mouse2.setDataSource(null);
    }

}
