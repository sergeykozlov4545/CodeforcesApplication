package com.example.sergey.codeforcesapplication.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contest")
data class Contest(
        @SerializedName("id")
        @PrimaryKey
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("phase")
        val phase: String,

        @SerializedName("startTimeSeconds")
        @ColumnInfo(name = "start_time_seconds")
        val startTimeSeconds: Long?,

        @SerializedName("durationSeconds")
        @ColumnInfo(name = "duration_seconds")
        val durationSeconds: Long
)