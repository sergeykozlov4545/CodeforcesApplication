package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("handle")
        val handle: String,

        @SerializedName("firstName")
        val firstName: String?,

        @SerializedName("lastName")
        val lastName: String?
)