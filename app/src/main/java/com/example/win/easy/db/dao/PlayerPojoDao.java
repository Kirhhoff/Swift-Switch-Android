package com.example.win.easy.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.win.easy.db.pojo.PlayerPojo;

@Dao
public interface PlayerPojoDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    long insert(PlayerPojo playerPojo);

}
