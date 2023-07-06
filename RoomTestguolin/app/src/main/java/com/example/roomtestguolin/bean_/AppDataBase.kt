package com.example.roomtestguolin.bean_

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class,WatchHis::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun hisDao():HisDao

    companion object {
        @Volatile
        private var sInstance: AppDataBase? = null
        private const val DATA_BASE_NAME = "jetpack_movie.db"

        @JvmStatic
        fun getInstance(context: Context): AppDataBase? {
            if (sInstance == null) {
                synchronized(AppDataBase::class.java) {
                    if (sInstance == null) {
                        sInstance = createInstance(context)
                    }
                }
            }
            return sInstance
        }



        private fun createInstance(context: Context): AppDataBase? {
            return Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DATA_BASE_NAME)
                .fallbackToDestructiveMigration()    //版本更改直接删除之前的数据库，重新再创建新的数据库
                .allowMainThreadQueries()   //允许在主线程查询数据
                .addMigrations(object : Migration(1,2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE user "
                                + " ADD COLUMN location varchar NOT NULL DEFAULT '8.0'")
                    }

                })
                .addMigrations(object : Migration(2,3) {
                    override fun migrate(database: SupportSQLiteDatabase) {
//                        database.execSQL("create TABLE IF NOT EXISTS 'wathchis'" +
//                                "  (`id` INTEGER PRIMARY KEY AUTOINCREMENT,'uid' text not null,url text not null);")
                        database.execSQL("CREATE TABLE IF NOT EXISTS `WatchHis` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `uid` TEXT NOT NULL, `url` TEXT NOT NULL)")
                    }

                })
                .build()
        }
    }
}
