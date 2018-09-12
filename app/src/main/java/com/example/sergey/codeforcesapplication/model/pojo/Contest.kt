package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class Contest(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("phase") val phase: String,
        @SerializedName("startTimeSeconds") val startTimeSeconds: Long?,
        @SerializedName("durationSeconds") val durationSeconds: Long
) {

    val isUpcomming: Boolean
        get() = phase == "BEFORE"

    val isCurrent: Boolean
        get() = phase == "CODING"

    val isPast: Boolean
        get() = phase in arrayOf("PENDING_SYSTEM_TEST", "SYSTEM_TEST", "FINISHED")
}