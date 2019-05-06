package com.example.win.easy.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.win.easy.db.pojo.MousePojo;
import com.example.win.easy.db.pojo.PlayerPojo;

import java.util.List;

public class Player {

    @Embedded
    PlayerPojo playerPojo;

    @Relation(
            parentColumn = "id",
            entityColumn = "playerPojoId"
    )
    List<MousePojo> mousePojoList;

}
