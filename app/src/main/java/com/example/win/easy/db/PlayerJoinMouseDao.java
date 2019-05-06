package com.example.win.easy.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.win.easy.db.pojo.MousePojo;
import com.example.win.easy.db.pojo.PlayerPojo;

import java.util.List;

@Dao
public interface PlayerJoinMouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlayerJoinMouse playerJoinMouse);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * " +
            "FROM mousePojo INNER JOIN playerjoinmouse " +
            "ON mousePojo.id=playerjoinmouse.mouseId " +
            "WHERE playerjoinmouse.playerId=:playerId")
    List<MousePojo> findAllMousePojosForPlayerById(long playerId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * " +
            "FROM playerpojo INNER JOIN playerjoinmouse " +
            "ON playerpojo.id=playerjoinmouse.playerId " +
            "WHERE playerjoinmouse.mouseId=:mouseId")
    List<PlayerPojo> findAllPlayerPojosForMouseById(long mouseId);
}
