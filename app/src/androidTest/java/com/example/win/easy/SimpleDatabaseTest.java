package com.example.win.easy;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.win.easy.domain.CompleteMouse;
import com.example.win.easy.domain.CompleteMouseDao;
import com.example.win.easy.domain.Mouse;
import com.example.win.easy.domain.MouseDao;
import com.example.win.easy.domain.MouseJoinParameter;
import com.example.win.easy.domain.MouseJoinParameterDao;
import com.example.win.easy.domain.Parameter;
import com.example.win.easy.domain.ParameterDAO;
import com.example.win.easy.domain.ThisDatabase;
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
    private MouseJoinParameterDao mouseJoinParameterDao;
    private CompleteMouseDao completeMouseDao;
    private Mouse mouse1=new Mouse();
    private Mouse mouse2=new Mouse();
    private Parameter parameter1=new Parameter();
    private Parameter parameter2=new Parameter();
    private MouseJoinParameter mouseJoinParameter1=new MouseJoinParameter();
    private MouseJoinParameter mouseJoinParameter2=new MouseJoinParameter();

    @Before
    public void createDb(){
        Context context= ApplicationProvider.getApplicationContext();
        thisDatabase = Room.inMemoryDatabaseBuilder(context, ThisDatabase.class).build();
        mouseDao= thisDatabase.mouseDao();
        parameterDAO= thisDatabase.parameterDAO();
        mouseJoinParameterDao=thisDatabase.mouseJoinParameterDao();
        completeMouseDao=thisDatabase.completeMouseDao();
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

        Long mid1=mouseDao.insert(mouse1);
        Long mid2=mouseDao.insert(mouse2);

        mouseJoinParameter1.setMouseId(mid1);
        mouseJoinParameter1.setParameterId(pid1);
        mouseJoinParameter2.setMouseId(mid2);
        mouseJoinParameter2.setParameterId(pid2);

        mouseJoinParameterDao.insert(mouseJoinParameter1);
        mouseJoinParameterDao.insert(mouseJoinParameter2);

        List<Mouse> mouseList=mouseDao.findAllMouses();
        for (Mouse mouse:mouseList){
            MouseJoinParameter mouseJoinParameter=mouseJoinParameterDao.findByMouseId(mouse.getId());
            Parameter parameter=parameterDAO.findById(mouseJoinParameter.getParameterId());
            CompleteMouse completeMouse=completeMouseDao.findById(mouse.getId());
            System.out.println(mouse+" :: "+mouseJoinParameter+" :: "+parameter+" :: "+completeMouse);
        }

        List<Parameter> parameterList=parameterDAO.findAllParameters();
        for (Parameter parameter:parameterList){
            MouseJoinParameter mouseJoinParameter=mouseJoinParameterDao.findByParameterId(parameter.getId());
            Mouse mouse=mouseDao.findById(mouseJoinParameter.getMouseId());
            System.out.println(parameter+" :: "+mouseJoinParameter+" :: "+mouse);
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
