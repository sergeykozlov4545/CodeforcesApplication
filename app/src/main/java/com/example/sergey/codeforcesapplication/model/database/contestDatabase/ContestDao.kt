package com.example.sergey.codeforcesapplication.model.database.contestDatabase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.sergey.codeforcesapplication.model.Contest

@Dao
interface ContestDao {
    @Query("select * from contest")
    fun loadAllContests(): List<Contest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contests: List<Contest>)
}