package com.example.sergey.codeforcesapplication.model.remote

import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("contest.list")
    fun getContestList(): Deferred<Response<List<Contest>>>

    @GET("contest.standings")
    fun getContestStandings(@Query("contestId") contestId: Long): Deferred<Response<ContestInfo>>

}