package com.example.sergey.codeforcesapplication.model.remote

import com.google.gson.annotations.SerializedName

data class Response<T>(
        @SerializedName("status") val status: String = "OK",
        @SerializedName("comment") val comment: String = "",
        @SerializedName("result") val result: T? = null
) {
    val isSuccess: Boolean
        get() = status == "OK"

    companion object {
        fun <T> FAILED(comment: String = "") = Response<T>(status = "FAILED", comment = comment)
        fun <T> OK(result: T) = Response(status = "OK", result = result)
    }
}