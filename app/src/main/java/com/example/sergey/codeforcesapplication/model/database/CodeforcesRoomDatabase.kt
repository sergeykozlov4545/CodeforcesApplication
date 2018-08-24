package com.example.sergey.codeforcesapplication.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sergey.codeforcesapplication.model.database.dao.ContestDao
import com.example.sergey.codeforcesapplication.model.pojo.Contest

@Database(
        version = 4,
        entities = [
            Contest::class
        ]
)
abstract class CodeforcesRoomDatabase : RoomDatabase() {
    abstract fun contestDao(): ContestDao
}