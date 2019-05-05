package com.example.win.easy.feature;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InternalInformation {

    @PrimaryKey
    int songId;

    public Date recentPlayedDate;//最近一次被播放时间
}
