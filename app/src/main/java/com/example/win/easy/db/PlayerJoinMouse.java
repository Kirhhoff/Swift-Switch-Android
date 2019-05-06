package com.example.win.easy.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.win.easy.db.pojo.MousePojo;
import com.example.win.easy.db.pojo.PlayerPojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = MousePojo.class,
                        parentColumns = "id",
                        childColumns = "mouseId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = PlayerPojo.class,
                        parentColumns = "id",
                        childColumns = "playerId",
                        onDelete = ForeignKey.CASCADE
                )
        },
        primaryKeys = {"playerId","mouseId"},
        indices = {
                @Index("playerId"),
                @Index("mouseId")
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoinMouse {

    public long playerId;

    public long mouseId;
}
