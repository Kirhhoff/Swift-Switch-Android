package com.example.win.easy.repository.db.data_object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.win.easy.enumeration.DataSource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongListDO {

    /**
     * 自动生成的歌单id
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    /**
     * 歌单的名字
     */
    private String name;

    /**
     * 歌单来源
     * @see DataSource
     */
    private DataSource source;

    /**
     * 歌单图片的网路url
     */
    private String avatarUrl;

    /**
     * 歌单图片的绝对路径
     */
    private String avatarPath;

    /**
     * 网络歌单的用户id
     */
    private String uid;

    /**
     * 网络歌单本身的id
     */
    private String remoteId;
}
