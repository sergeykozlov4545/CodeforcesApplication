package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.cache.CacheManager
import com.example.sergey.codeforcesapplication.model.cache.CacheObjectKey
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange
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
    fun getUserRatingChangesList(handle: String): Deferred<Response<List<RatingChange>>>
}

@Suppress("UNCHECKED_CAST")
class ContestsRepositoryImpl(
        private val serviceApi: ServiceApi,
        private val cacheManager: CacheManager
) : ContestsRepository {

    private val mutex = Mutex()

    override fun getContests(): Deferred<Response<List<Contest>>> = async {
        mutex.withLock {
            val cachedContests =
                    cacheManager.getValue(CacheObjectKey.CONTESTS_LIST) as? List<Contest>

            if (cachedContests != null) {
                return@withLock Response.OK(result = cachedContests)
            }

            return@withLock loadContests().await().apply {
                if (isSuccess) {
                    cacheManager.putValue(
                            key = CacheObjectKey.CONTESTS_LIST,
                            value = this.result,
                            timeToLiveMillis = ONE_HOUR
                    )
                }
            }
        }
    }

    override fun getContestStandings(contestId: Long): Deferred<Response<ContestInfo>> = async {
        mutex.withLock {
            var cachedMapContestsStandings =
                    cacheManager.getValue(CacheObjectKey.CONTEST_STANDINGS)
                            as? MutableMap<Long, ContestInfo>

            if (cachedMapContestsStandings == null) {
                cachedMapContestsStandings = HashMap()
                cacheManager.putValue(
                        key = CacheObjectKey.CONTEST_STANDINGS,
                        value = cachedMapContestsStandings,
                        timeToLiveMillis = ONE_HOUR
                )

                return@withLock loadContestStandings(contestId).await().apply {
                    if (isSuccess) cachedMapContestsStandings[contestId] = this.result!!

                }
            }

            val cachedContestsStandings = cachedMapContestsStandings[contestId]
                    ?: return@withLock loadContestStandings(contestId).await().apply {
                        if (isSuccess) cachedMapContestsStandings[contestId] = this.result!!
                    }

            return@withLock Response.OK(cachedContestsStandings)
        }
    }

    override fun getUsersInfo(handlers: List<String>) = async {
        mutex.withLock {
            var cachedUsers =
                    cacheManager.getValue(CacheObjectKey.USERS)
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
                return@withLock Response.OK(users.toList())
            }

            val response = loadUsersInfo(handlers.filter { it !in cachedUsers }).await()
            if (!response.isSuccess) {
                return@withLock Response.FAILED<List<User>>(response.comment)
            }

            response.result?.let {
                users.addAll(response.result)
                response.result.forEach { user -> cachedUsers[user.handle] = user }
            }

            return@withLock Response.OK(users.toList())
        }
    }

    override fun getUserRatingChangesList(handle: String) = async {
        mutex.withLock {
            var cachedRatingInfo =
                    cacheManager.getValue(CacheObjectKey.RATINGS)
                            as? MutableMap<String, List<RatingChange>>

            if (cachedRatingInfo == null) {
                cachedRatingInfo = HashMap()
                cacheManager.putValue(
                        key = CacheObjectKey.RATINGS,
                        value = cachedRatingInfo,
                        timeToLiveMillis = ONE_HOUR
                )

                return@async loadRatingInfo(handle).await().apply {
                    if (isSuccess) cachedRatingInfo[handle] = this.result!!
                }
            }

            val cachedHandleRatingInfo = cachedRatingInfo[handle]
            if (cachedHandleRatingInfo != null) {
                return@async Response.OK(cachedHandleRatingInfo)
            }

            return@withLock loadRatingInfo(handle).await().apply {
                if (isSuccess) cachedRatingInfo[handle] = this.result!!
            }
        }
    }

    private fun loadContests() = serviceApi.getContestList()

    // TODO: После добавления экрана настроек получать сколько загружать вместо 100
    private fun loadContestStandings(contestId: Long) =
            serviceApi.getContestStandings(contestId, 100)

    private fun loadUsersInfo(handlers: List<String>) =
            serviceApi.getUsersInfo(handlers.joinToString(separator = ";"))

    private fun loadRatingInfo(handle: String) = serviceApi.getUserRatingChangesList(handle)

    companion object {
        private val ONE_HOUR = TimeUnit.HOURS.toMillis(1)
    }
}