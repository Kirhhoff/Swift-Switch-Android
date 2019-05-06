package com.example.win.easy.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.win.easy.db.Player;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM playerpojo WHERE id=:playerId")
    Player findById(long playerId);
}
