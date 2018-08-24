package com.example.sergey.codeforcesapplication.model.database

import android.arch.persistence.room.Room
import android.content.Context
import com.example.sergey.codeforcesapplication.model.Contest
import com.example.sergey.codeforcesapplication.model.database.contestDatabase.ContestDatabase
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

interface DataBaseManager {
    suspend fun loadAllContests(): Deferred<List<Contest>>
    suspend fun saveAllContests(contests: List<Contest>): Deferred<Unit>
}

class DataBaseManagerImpl(context: Context) : DataBaseManager {

    private val database =
            Room.databaseBuilder(context.applicationContext, ContestDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    override suspend fun loadAllContests(): Deferred<List<Contest>> = async {
        database.contestDao().loadAllContests()
    }

    override suspend fun saveAllContests(contests: List<Contest>): Deferred<Unit> = async {
        database.contestDao().insertAll(contests)
    }

    companion object {
        private const val DB_NAME = "contests_db"
    }
}