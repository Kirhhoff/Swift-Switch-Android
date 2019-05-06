package com.example.win.easy.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.win.easy.db.dao.MousePojoDao;
import com.example.win.easy.db.dao.ParameterDAO;
import com.example.win.easy.db.dao.PlayerPojoDao;
import com.example.win.easy.db.pojo.MousePojo;
import com.example.win.easy.db.pojo.PlayerPojo;

@Database(entities = {MousePojo.class,Parameter.class, PlayerPojo.class,PlayerJoinMouse.class},version = 1)
@TypeConverters({CustomTypeConverters.class})
public abstract class ThisDatabase extends RoomDatabase {

    public abstract MousePojoDao mousePojoDao();

    public abstract ParameterDAO parameterDAO();

    public abstract PlayerPojoDao playerPojoDao();

    public abstract PlayerJoinMouseDao playerJoinMouseDao();
}
