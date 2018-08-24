package com.example.sergey.codeforcesapplication.model.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceApiFactory {
    private const val BASE_URL = "https://codeforces.com/api/"

    fun create(): ServiceApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        return retrofit.create(ServiceApi::class.java)
    }
}