package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.cache.CacheManager
import com.example.sergey.codeforcesapplication.model.cache.CacheObjectKey
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import com.example.sergey.codeforcesapplication.model.pojo.User
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
    fun getUsersInfo(handlers: List<String>): Deferred<Response<List<User>>>
}

class ContestsRepositoryImpl(
        private val serviceApi: ServiceApi,
        private val cacheManager: CacheManager
) : ContestsRepository {

    private val mutex = Mutex()

    override fun getContests() = async {
        mutex.withLock {
            // TODO: Не хранить Response, а хранить List<Contest>
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
            // TODO: Не хранить Response, а хранить ContestInfo
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

    override fun getUsersInfo(handlers: List<String>): Deferred<Response<List<User>>> = async {
        mutex.withLock {
            var cachedUsers = cacheManager.getValue(CacheObjectKey.USERS)
                    as? MutableMap<String, User>

            if (cachedUsers == null) {
                cachedUsers = HashMap()
                cacheManager.putValue(
                        key = CacheObjectKey.USERS,
                        value = cachedUsers,
                        timeToLiveMillis = ONE_HOUR
                )

                return@withLock loadUsersInfo(handlers).await().apply {
                    if (isSuccess) {
                        result?.forEach { user -> cachedUsers[user.handle] = user }
                    }
                }
            }

            val users = cachedUsers.filter { it.key in handlers }.values.toMutableList()

            if (users.size == handlers.size) {
                return@withLock Response(result = users.toList())
            }

            val response = loadUsersInfo(handlers.filter { it !in cachedUsers }).await()
            if (response.isSuccess) {
                response.result?.let {
                    users.addAll(response.result)
                    response.result.forEach { user -> cachedUsers[user.handle] = user }
                }
            }

            return@withLock Response(
                    status = response.status,
                    comment = response.comment,
                    result = if (response.isSuccess) users.toList() else null
            )
        }
    }

    private fun loadContests() = async {
        serviceApi.getContestList().await()
    }

    // TODO: После добавления экрана настроек получать сколько загружать вместо 100
    private fun loadContestStandings(contestId: Long) = async {
        serviceApi.getContestStandings(contestId, 100).await()
    }

    private fun loadUsersInfo(handlers: List<String>) = async {
        serviceApi.getUsersInfo(handlers.joinToString(separator = ";")).await()
    }

    companion object {
        private val ONE_HOUR = TimeUnit.HOURS.toMillis(1)
    }
}