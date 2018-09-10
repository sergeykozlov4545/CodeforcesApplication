package com.example.sergey.codeforcesapplication.model.remote

import com.google.gson.annotations.SerializedName

data class Response<T>(
        @SerializedName("status")
        val status: String = "OK",

        @SerializedName("comment")
        val comment: String? = null,

        @SerializedName("result")
        val result: T
) {
    val isSuccess: Boolean
        get() = status == "OK"
}