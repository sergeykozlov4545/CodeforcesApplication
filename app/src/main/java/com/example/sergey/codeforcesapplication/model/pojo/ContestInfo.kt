package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class ContestInfo(
        @SerializedName("problems") val problems: List<Problem>,
        @SerializedName("rows") val ranks: List<RankListRow>
)