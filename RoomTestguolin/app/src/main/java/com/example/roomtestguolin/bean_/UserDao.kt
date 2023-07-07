package com.example.roomtestguolin.bean_

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(vararg users: User?): LongArray?
    @Query("select * from user")
    fun allUsers() : List<User>
}