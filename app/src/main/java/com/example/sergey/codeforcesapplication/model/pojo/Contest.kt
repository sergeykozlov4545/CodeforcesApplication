package com.example.sergey.codeforcesapplication.model.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contest")
data class Contest(
        @SerializedName("id")
        @PrimaryKey
        val id: Long,

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
) {

    val isUpcomming: Boolean
        get() = phase == "BEFORE"

    val isCurrent: Boolean
        get() = phase == "CODING"

    val isPast: Boolean
        get() = phase in arrayOf("PENDING_SYSTEM_TEST", "SYSTEM_TEST", "FINISHED")
}