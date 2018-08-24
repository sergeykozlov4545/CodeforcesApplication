package com.example.sergey.codeforcesapplication.model.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.sergey.codeforcesapplication.model.pojo.Problem

@Dao
interface ProblemDao {
    @Query("select * from problem")
    fun loadAllProblems(): List<Problem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(problems: List<Problem>)
}