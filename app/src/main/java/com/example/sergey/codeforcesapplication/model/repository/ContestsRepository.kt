package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.database.DataBaseManager
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange
import com.example.sergey.codeforcesapplication.model.pojo.User
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManager
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import java.util.*
import java.util.concurrent.TimeUnit

interface ContestsRepository {
    fun getContests(): Deferred<Response<List<Contest>>>
    fun getContestStandings(contestId: Long): Deferred<Response<ContestInfo>>
    fun getUsersInfo(handlers: List<String>): Deferred<Response<List<User>>>
    fun getUserRatingChangesList(handle: String): Deferred<Response<List<RatingChange>>>
}

class ContestsRepositoryImpl(
        private val serviceApi: ServiceApi,
        private val dataBaseManager: DataBaseManager,
        private val preferencesManager: PreferencesManager
) : ContestsRepository {
    private val mutex = Mutex()

    override fun getContests(): Deferred<Response<List<Contest>>> = async {
        mutex.withLock {
            val contests = dataBaseManager.loadAllContests().await()
            if (!contests.isEmpty()) {
                val lastLoadedTime = preferencesManager.getLong(LAST_LOADED_TIME_CONTESTS_KEY, 0)
                if (Date().time - lastLoadedTime < ONE_HOUR) {
                    return@withLock Response(result = contests)
                }
            }

            val contestsResponse = serviceApi.getContestList().await()
            return@withLock contestsResponse.apply {
                if (isSuccess) {
                    dataBaseManager.saveAllContests(contestsResponse.result).await()
                    preferencesManager.putLong(LAST_LOADED_TIME_CONTESTS_KEY, Date().time)
                }
            }
        }
    }

    override fun getContestStandings(contestId: Long): Deferred<Response<ContestInfo>> = async {
        // TODO: После добавления экрана настроек получать сколько загружать вместо 100
        mutex.withLock { serviceApi.getContestStandings(contestId, 100).await() }
    }

    override fun getUsersInfo(handlers: List<String>): Deferred<Response<List<User>>> = async {
        mutex.withLock { serviceApi.getUsersInfo(handlers.joinToString(separator = ";")).await() }
    }

    override fun getUserRatingChangesList(handle: String): Deferred<Response<List<RatingChange>>> = async {
        mutex.withLock { serviceApi.getUserRatingChangesList(handle).await() }
    }

    companion object {
        private const val LAST_LOADED_TIME_CONTESTS_KEY = "last_loaded_time_contests"
        // TODO: Когда добавится экран настроек, там можно указать время жизни
        private val ONE_HOUR = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    }
}