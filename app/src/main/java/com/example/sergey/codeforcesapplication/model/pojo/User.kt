package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("handle")
        val handle: String,

        @SerializedName("firstName")
        val firstName: String?,

        @SerializedName("lastName")
        val lastName: String?,

        @SerializedName("avatar")
        val avatarUrl: String
) {
    val fullName: String
        get() {
            val firstName = this.firstName ?: ""
            val lastName = this.lastName ?: ""
            return "$firstName $lastName"
        }
}