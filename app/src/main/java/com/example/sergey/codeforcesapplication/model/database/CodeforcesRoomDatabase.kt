package com.example.sergey.codeforcesapplication.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sergey.codeforcesapplication.model.database.dao.ContestDao
import com.example.sergey.codeforcesapplication.model.database.dao.ProblemDao
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.Problem

@Database(entities = [Contest::class, Problem::class], version = 1)
abstract class CodeforcesRoomDatabase : RoomDatabase() {
    abstract fun contestDao(): ContestDao
    abstract fun problemDao(): ProblemDao
}