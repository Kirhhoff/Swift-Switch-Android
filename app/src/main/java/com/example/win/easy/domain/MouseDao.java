package com.example.win.easy.domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Mouse mouse);

    @Query("SELECT * FROM mmouse")
    List<Mouse> findAllMouses();

}
