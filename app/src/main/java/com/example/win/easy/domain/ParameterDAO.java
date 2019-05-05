package com.example.win.easy.domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ParameterDAO {

    @Insert(onConflict = REPLACE)
    long insert(Parameter parameter);

    @Query("SELECT * FROM param WHERE id=:id")
    Parameter findById(long id);

    @Query("SELECT * FROM param")
    List<Parameter> findAllParameters();

}
