package com.example.sergey.codeforcesapplication.model.remote

import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface ServiceApi {

    @GET("contest.list")
    fun getContestsList(): Deferred<Response<Contest>>

}