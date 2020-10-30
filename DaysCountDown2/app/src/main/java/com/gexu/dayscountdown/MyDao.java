package com.gexu.dayscountdown;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
//database access object
public interface MyDao {
    //添加
    @Insert
    void InsertDateAndEvent(DateAndEvent...dateAndEvents);
    //更新
    @Update
    void UpdateDateAndEvent(DateAndEvent...dateAndEvents);
    //删除
    @Delete
    void DeleteDateAndEvent(DateAndEvent...dateAndEvents);
    //清空
    @Query("DELETE FROM DateAndEvent")
    void DeleteAllDateAndEvent();
    //获取所有数据
    @Query("SELECT * FROM DateAndEvent ORDER BY ID DESC")
    LiveData<List<DateAndEvent>> getAllDateAndEvent();





}
