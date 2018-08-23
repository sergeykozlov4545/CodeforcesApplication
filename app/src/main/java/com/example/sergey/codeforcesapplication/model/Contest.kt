package com.example.sergey.codeforcesapplication.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contest")
data class Contest(
        @PrimaryKey val id: Int,
        val name: String,
        val phase: String,
        @ColumnInfo(name = "start_time_seconds") val startTimeSeconds: Long?,
        @ColumnInfo(name = "duration_seconds") val durationSeconds: Long
)