package com.example.win.easy.domain;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Mouse.class,Parameter.class},version = 1)
@TypeConverters({CustomTypeConverters.class})
public abstract class ThisDatabase extends RoomDatabase {

    public abstract MouseDao mouseDao();

    public abstract ParameterDAO parameterDAO();

}
