package com.example.win.easy.repository.db.data_object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.win.easy.enumeration.DataSource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDO {

    /**
     * 自动生成的歌曲id
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    /**
     * 歌曲名字，用于展示给用户
     * 如“陈奕迅 - 烟味”
     */
    private String name;

    /**
     * 歌曲作者
     */
    private String author;

    /**
     * 歌曲的识别序列
     */
    private List<Character> sequence;

    /**
     * 歌曲来源
     * @see DataSource
     */
    private DataSource source;

    /**
     * 歌曲网路url
     */
    private String songUrl;

    /**
     * 歌曲头像网路url
     */
    private String avatarUrl;

    /**
     * 歌曲的绝对路径
     */
    private String songPath;

    /**
     * 歌曲图片的绝对路径
     */
    private String avatarPath;

    /**
     * 网络歌曲的用户id
     */
    private String uid;

    /**
     * 网络歌曲的id
     */
    private String remoteId;

}
