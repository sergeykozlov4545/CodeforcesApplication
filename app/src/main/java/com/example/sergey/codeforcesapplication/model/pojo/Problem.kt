package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class Problem(
        @SerializedName("contestId") val contestId: Long,
        @SerializedName("index") val index: String,
        @SerializedName("name") val name: String,
        @SerializedName("points") val points: Double?
)