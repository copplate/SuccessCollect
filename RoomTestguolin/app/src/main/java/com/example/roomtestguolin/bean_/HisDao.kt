package com.example.roomtestguolin.bean_

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HisDao {
    @Insert
    fun insert(vararg hises: WatchHis?): LongArray?
    @Query("select * from WatchHis")
    fun allHises() : List<WatchHis>
}