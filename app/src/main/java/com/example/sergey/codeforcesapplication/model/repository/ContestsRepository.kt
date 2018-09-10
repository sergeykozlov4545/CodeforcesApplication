package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock

interface ContestsRepository {
    fun getContests(): Deferred<Response<List<Contest>>>
}

class ContestsRepositoryImpl(private val serviceApi: ServiceApi) : ContestsRepository {

    private val mutex = Mutex()

    override fun getContests(): Deferred<Response<List<Contest>>> = async {
        mutex.withLock { serviceApi.getContestList().await() }
    }
}