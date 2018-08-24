package com.example.sergey.codeforcesapplication.model.remote

import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface ServiceApi {

    @GET("contest.list")
    fun getContestList(): Deferred<Response<Contest>>

    @GET("contest.standings")
    fun getContestStandings(): Deferred<Response<ContestInfo>>

}