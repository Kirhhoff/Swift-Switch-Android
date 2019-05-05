package com.example.win.easy.domain;


import androidx.room.Entity;
import androidx.room.ForeignKey;

import lombok.Data;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Mouse.class,
                parentColumns = "id",
                childColumns = "mouseId",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Parameter.class,
                parentColumns = "id",
                childColumns = "parameterId",
                onDelete = ForeignKey.CASCADE
        )
},primaryKeys = {
        "mouseId",
        "parameterId"
})
@Data
public class MouseJoinParameter {

    public long mouseId;

    public long parameterId;

}
