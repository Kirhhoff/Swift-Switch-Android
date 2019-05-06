package com.example.win.easy.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.win.easy.db.pojo.MousePojo;

import java.util.List;

@Dao
public interface MousePojoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(MousePojo mousePojo);

    @Query("SELECT * FROM MousePojo")
    List<MousePojo> findAllMouses();

}
