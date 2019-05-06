package com.example.win.easy.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.win.easy.db.Parameter;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ParameterDAO {

    @Insert(onConflict = REPLACE)
    long insert(Parameter parameter);

    @Query("SELECT * FROM param WHERE id=:id")
    Parameter findById(long id);

}
