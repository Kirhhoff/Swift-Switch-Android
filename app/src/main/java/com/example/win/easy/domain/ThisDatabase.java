package com.example.win.easy.domain;

import androidx.room.Database;
import androidx.room.DatabaseView;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Mouse.class,Parameter.class,MouseJoinParameter.class},views = {CompleteMouse.class},version = 1)
@DatabaseView
@TypeConverters({CustomTypeConverters.class})
public abstract class ThisDatabase extends RoomDatabase {

    public abstract MouseDao mouseDao();

    public abstract ParameterDAO parameterDAO();

    public abstract CompleteMouseDao completeMouseDao();

    public abstract MouseJoinParameterDao mouseJoinParameterDao();

}
