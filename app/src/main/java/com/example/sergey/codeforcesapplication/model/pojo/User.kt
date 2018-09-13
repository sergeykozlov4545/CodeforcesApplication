package com.example.sergey.codeforcesapplication.model.pojo

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("avatar") val avatarUrl: String,
        @SerializedName("titlePhoto") val titlePhoto: String,
        @SerializedName("handle") val handle: String,
        @SerializedName("firstName") val firstName: String?,
        @SerializedName("lastName") val lastName: String?,
        @SerializedName("country") val country: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("registrationTimeSeconds") val registrationTimeSeconds: Long,
        @SerializedName("email") val email: String?,
        @SerializedName("vkId") val vkId: String?,
        @SerializedName("openId") val openId: String?,
        @SerializedName("friendOfCount") val friendOfCount: Int,
        @SerializedName("contribution") val contribution: Int,
        @SerializedName("rank") val rank: String,
        @SerializedName("rating") val rating: Int,
        @SerializedName("maxRank") val maxRank: String,
        @SerializedName("maxRating") val maxRating: Int
) {
    val fullName: String
        get() {
            val firstName = this.firstName ?: ""
            val lastName = this.lastName ?: ""
            return "$firstName $lastName"
        }
}