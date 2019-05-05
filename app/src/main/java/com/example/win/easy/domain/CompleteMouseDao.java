package com.example.win.easy.domain;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CompleteMouseDao {

    @Query("SELECT * FROM completemouse WHERE id=:completeMouseId")
    CompleteMouse findById(long completeMouseId);

}
