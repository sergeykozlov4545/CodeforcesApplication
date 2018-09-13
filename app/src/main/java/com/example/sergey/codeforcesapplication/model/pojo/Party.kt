package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class Party(
        @SerializedName("members") val members: List<Member>,
        @SerializedName("teamName") val teamName: String?
) {
    val membersString: String
        get() = members.joinToString(separator = ",") { it.handle }
}