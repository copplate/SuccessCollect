package com.example.roomtestguolin.bean_

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User()  {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "uid")
    lateinit var uid:String
    lateinit var nickName:String
    lateinit var sex:String

//    lateinit var location:String

}