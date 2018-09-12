package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class RankListRow(
        @SerializedName("party") val party: Party,
        @SerializedName("rank") val rank: Int,
        @SerializedName("points") val points: Double
)