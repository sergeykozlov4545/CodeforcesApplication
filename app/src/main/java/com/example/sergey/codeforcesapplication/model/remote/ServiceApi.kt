package com.example.sergey.codeforcesapplication.model.remote

import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange
import com.example.sergey.codeforcesapplication.model.pojo.User
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("contest.list?lang=ru")
    fun getContestList(): Deferred<Response<List<Contest>>>

    @GET("contest.standings?lang=ru&showUnofficial=false&from=1")
    fun getContestStandings(@Query("contestId") contestId: Long,
                            @Query("count") count: Int): Deferred<Response<ContestInfo>>

    @GET("user.info?lang=ru")
    fun getUsersInfo(@Query("handles") handles: String): Deferred<Response<List<User>>>

    @GET("user.rating?lang=ru")
    fun getUserRatingChangesList(@Query("handle") handle: String): Deferred<Response<List<RatingChange>>>
}