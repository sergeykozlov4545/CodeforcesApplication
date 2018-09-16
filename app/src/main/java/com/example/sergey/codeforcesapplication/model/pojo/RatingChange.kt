package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class RatingChange(
        @SerializedName("contestId") val contestId: Int,
        @SerializedName("contestName") val contestName: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("oldRating") val oldRating: Int,
        @SerializedName("newRating") val newRating: Int
)