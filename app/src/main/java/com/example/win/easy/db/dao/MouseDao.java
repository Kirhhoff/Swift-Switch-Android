package com.example.win.easy.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.win.easy.db.Mouse;

@Dao
public interface MouseDao {

    @Query("SELECT * FROM mousePojo WHERE id=:mouseId")
    Mouse findById(long mouseId);


}
