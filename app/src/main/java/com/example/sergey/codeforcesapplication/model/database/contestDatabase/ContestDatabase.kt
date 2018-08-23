package com.example.sergey.codeforcesapplication.model.database.contestDatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sergey.codeforcesapplication.model.Contest

@Database(entities = [Contest::class], version = 2)
abstract class ContestDatabase : RoomDatabase() {
    abstract fun contestDao(): ContestDao
}