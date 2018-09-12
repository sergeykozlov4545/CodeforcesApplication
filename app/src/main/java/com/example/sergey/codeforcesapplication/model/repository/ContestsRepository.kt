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

    override fun getContests() = async {
        mutex.withLock {
            val cachedContestsListResponse =
                    cacheManager.getValue(CacheObjectKey.CONTESTS_LIST) as? Response<List<Contest>>

            return@withLock cachedContestsListResponse
                    ?: loadContests().await().apply {
                        if (isSuccess) cacheManager.putValue(
                                key = CacheObjectKey.CONTESTS_LIST,
                                value = this,
                                timeToLiveMillis = ONE_HOUR
                        )
                    }
        }
    }

    override fun getContestStandings(contestId: Long) = async {
        mutex.withLock {
            var cachedContestsStandings =
                    cacheManager.getValue(CacheObjectKey.CONTEST_STANDINGS) as? MutableMap<Long, Response<ContestInfo>>

            if (cachedContestsStandings == null) {
                cachedContestsStandings = HashMap()
                cacheManager.putValue(
                        key = CacheObjectKey.CONTEST_STANDINGS,
                        value = cachedContestsStandings,
                        timeToLiveMillis = ONE_HOUR
                )

                return@withLock loadContestStandings(contestId).await().apply {
                    if (isSuccess) cachedContestsStandings[contestId] = this
                }
            }

            return@withLock cachedContestsStandings[contestId]
                    ?: loadContestStandings(contestId).await().apply {
                        if (isSuccess) cachedContestsStandings[contestId] = this
                    }
        }
    }

    private fun loadContests() = async {
        serviceApi.getContestList().await()
    }

    // TODO: После добавления экрана настроек получать сколько загружать вместо 100
    private fun loadContestStandings(contestId: Long) = async {
        serviceApi.getContestStandings(contestId, 100).await()
    }

    companion object {
        private val ONE_HOUR = TimeUnit.HOURS.toMillis(1)
    }
}