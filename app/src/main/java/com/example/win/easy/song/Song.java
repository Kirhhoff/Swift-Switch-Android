package com.example.win.easy.song;

/**
 * 歌曲的各种信息及其set和get函数
 */

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.win.easy.feature.InternalInformation;

import java.io.File;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public String author;

    public String abpath;

    public List<Character> sequence;

    public DataSource source;

    public InternalInformation internalInformation;

    public File absolutePath;

    public Song(String name){
        this.name=name;
    }

    public Song(String abpath,String name){
        this.abpath=abpath;
        this.name=name;
    }
}
