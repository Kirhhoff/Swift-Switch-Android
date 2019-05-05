package com.example.win.easy.domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.win.easy.song.DataSource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "mmouse",foreignKeys = {
        @ForeignKey(
                entity = Parameter.class,
                parentColumns = "id",
                childColumns = "paramId",
                onDelete = CASCADE,
                deferred = true
        )
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mouse {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String band;

    public int version;

    public List<Character> sequence;

    public DataSource dataSource;

    public long paramId;

}
