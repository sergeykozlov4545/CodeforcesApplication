package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.cache.CacheManager
import com.example.sergey.codeforcesapplication.model.cache.CacheObjectKey
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import java.util.concurrent.TimeUnit

interface ContestsRepository {
    fun getContests(): Deferred<Response<List<Contest>>>
    fun getContestStandings(contestId: Long): Deferred<Response<ContestInfo>>
}

class ContestsRepositoryImpl(
        private val serviceApi: ServiceApi,
        private val cacheManager: CacheManager
) : ContestsRepository {

    private val mutex = Mutex()

    override fun getContests(): Deferred<Response<List<Contest>>> = async {
        mutex.withLock {
            val cachedContestsListResponse = cacheManager.getValue(CacheObjectKey.CONTESTS_LIST) as? Response<List<Contest>>
            if (cachedContestsListResponse != null) {
                return@withLock cachedContestsListResponse!!
            }

            val contestsListResponse = serviceApi.getContestList().await()
            if (contestsListResponse.isSuccess) {
                cacheManager.putValue(
                        key = CacheObjectKey.CONTESTS_LIST,
                        value = contestsListResponse,
                        timeToLiveMillis = ONE_HOUR
                )
            }

            return@withLock contestsListResponse
        }
    }

    override fun getContestStandings(contestId: Long): Deferred<Response<ContestInfo>> = async {
        // TODO: После добавления экрана настроек получать сколько загружать вместо 100
        mutex.withLock { serviceApi.getContestStandings(contestId, 100).await() }
    }

    companion object {
        private val ONE_HOUR = TimeUnit.HOURS.toMillis(1)
    }
}