package com.example.roomtestguolin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomtestguolin.bean_.*

class MainActivity : AppCompatActivity() {
    private var appDataBase: AppDataBase? = null
    private lateinit var userDao: UserDao
    private lateinit var hisDao: HisDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = User()
        val watchHis = WatchHis()
        val watchHis2 = WatchHis()
        val watchHis3 = WatchHis()
        /*user.uid = "555"
        user.nickName = "john"
        user.sex = "男"*/
        user.uid = "556"
        user.nickName = "rose"
        user.sex = "nv"
//        user.location = "长沙"

        watchHis.uid = "305"
        watchHis2.uid = "305"
        watchHis3.uid = "305"
        watchHis.url = "www.a"
        watchHis2.url = "www.a"
        watchHis3.url = "www.a"
//        val watchHises = arrayListOf<WatchHis>(watchHis, watchHis2, watchHis3)
        appDataBase = AppDataBase.getInstance(this)
        val userDao1 = appDataBase?.userDao()
        val hisDao = appDataBase?.hisDao()
        userDao1?.insert(user)
        hisDao?.insert(watchHis,watchHis2,watchHis3)
    }
}