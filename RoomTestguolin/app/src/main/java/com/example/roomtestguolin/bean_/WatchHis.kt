package com.example.roomtestguolin.bean_

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WatchHis {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    lateinit var uid:String
    lateinit var url:String
}