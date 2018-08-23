package com.example.sergey.codeforcesapplication.model.remote

import com.google.gson.annotations.SerializedName

class Response<T>(
        @SerializedName("status")
        val status: String,

        @SerializedName("comment")
        val comment: String,

        @SerializedName("result")
        val result: List<T>
)