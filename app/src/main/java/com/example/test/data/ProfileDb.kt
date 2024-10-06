package com.example.test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities =
    [
        ProfileEntity::class
    ],
    version = 1
)
abstract class ProfileDb : RoomDatabase() {

    abstract val profileDao : ProfileDao

    companion object{
        fun createDataBase(context : Context) : ProfileDb {
            return Room.databaseBuilder(
                context,
                ProfileDb::class.java,
                "profile.db"
            ).build()
        }
    }
}