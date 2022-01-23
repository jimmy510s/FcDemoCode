package com.example.fieldcodedemoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fieldcodedemoapp.data.dao.PostDao
import com.example.fieldcodedemoapp.data.model.Post
import com.example.fieldcodedemoapp.misc.MyApplication


@Database(entities = [Post::class], version = 3)

abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    object DatabaseBuilder {

        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {

            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = buildRoomDB(MyApplication.appContext)
                }
            }
            return instance!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"fieldcodedb").fallbackToDestructiveMigration()
                .build()

    }
}