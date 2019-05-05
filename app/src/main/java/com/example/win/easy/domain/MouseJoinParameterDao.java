package com.example.win.easy.domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MouseJoinParameterDao {

    @Query("SELECT * FROM mousejoinparameter WHERE mouseId=:mouseId")
    MouseJoinParameter findByMouseId(long mouseId);

    @Query("SELECT * FROM mousejoinparameter WHERE parameterId=:parameterId")
    MouseJoinParameter findByParameterId(long parameterId);

    @Insert
    void insert(MouseJoinParameter mouseJoinParameter);

}
