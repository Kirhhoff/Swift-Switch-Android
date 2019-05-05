package com.example.win.easy.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.win.easy.song.DataSource;

import java.util.List;

import lombok.Data;

@Entity(tableName = "mmouse")
@Data
public class Mouse {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String band;

    public int version;

    public List<Character> sequence;

    public DataSource dataSource;

}
