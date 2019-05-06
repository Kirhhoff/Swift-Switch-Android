package com.example.win.easy.db.pojo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.win.easy.db.Parameter;
import com.example.win.easy.song.DataSource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "mousePojo",foreignKeys = {
        @ForeignKey(
                entity = Parameter.class,
                parentColumns = "id",
                childColumns = "paramId",
                onDelete = CASCADE,
                deferred = true
        ),
},indices = {
        @Index("paramId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MousePojo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String band;

    public int version;

    public List<Character> sequence;

    public DataSource dataSource;

    public long paramId;

}
