package com.example.win.easy.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(tableName = "param")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {

    @PrimaryKey(autoGenerate = true)
    long id;

    public int weight;

    public int size;

}
