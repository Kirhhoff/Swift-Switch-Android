package com.example.win.easy.db;


import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.win.easy.db.pojo.MousePojo;
import com.example.win.easy.db.pojo.PlayerPojo;

import java.util.List;

public class Mouse {

    @Embedded
    MousePojo mousePojo;

    @Relation(
            parentColumn = "id",
            entityColumn = "mousePojoId"
    )
    List<PlayerPojo> playerPojoList;

}
