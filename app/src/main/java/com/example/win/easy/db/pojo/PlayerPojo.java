package com.example.win.easy.db.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity
@Data
public class PlayerPojo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

}
