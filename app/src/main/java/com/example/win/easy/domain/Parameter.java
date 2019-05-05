package com.example.win.easy.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "param")
@Data
public class Parameter {

    @PrimaryKey(autoGenerate = true)
    long id;

    public int weight;

    public int size;

}
