package com.example.sergey.codeforcesapplication.model.database.contestDatabase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.sergey.codeforcesapplication.model.Contest

@Dao
interface ContestDao {
    @Query("select * from contest where phase=\"BEFORE\"")
    fun loadUncommingContests(): List<Contest>

    @Query("select * from contest where phase=\"CODING\"")
    fun loadCurrentContests(): List<Contest>

    @Query("select * from contest where phase=\"PENDING_SYSTEM_TEST\" or phase=\"SYSTEM_TEST\" or phase=\"FINISHED\"")
    fun loadPastContests(): List<Contest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contests: List<Contest>)
}