package com.example.win.easy.domain;


import androidx.room.DatabaseView;

import com.example.win.easy.song.DataSource;

import java.util.List;

import lombok.Data;

@DatabaseView("SELECT mmouse.id,mmouse.band,mmouse.version,mmouse.sequence,mmouse.dataSource,param.weight,param.size "+
        "FROM mmouse INNER JOIN param "+
        "ON mmouse.id = param.id")
@Data
public class CompleteMouse {

    public long id;

    public String band;

    public int version;

    public List<Character> sequence;

    public DataSource dataSource;

    public int weight;

    public int size;

}
