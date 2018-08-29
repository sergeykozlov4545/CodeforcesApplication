package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.database.DataBaseManager
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.pojo.ContestInfo
import com.example.sergey.codeforcesapplication.model.pojo.User
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManager
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.sync.Mutex
import java.util.*
import java.util.concurrent.TimeUnit

interface ContestsRepository {
    fun getUncommingContests(): Deferred<List<Contest>>
    fun getCurrentContests(): Deferred<List<Contest>>
    fun getPastContests(): Deferred<List<Contest>>
    fun getContestStandings(contestId: Long): Deferred<ContestInfo>
    fun getUsersInfo(handlers: List<String>): Deferred<List<User>>
}

class ContestsRepositoryImpl(
        private val serviceApi: ServiceApi,
        private val dataBaseManager: DataBaseManager,
        private val preferencesManager: PreferencesManager
) : ContestsRepository {
    private val mutex = Mutex()

    override fun getUncommingContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            loadAndSaveContests(predicate = Contest::isUpcomming)
        } finally {
            mutex.unlock()
        }
    }

    override fun getCurrentContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            loadAndSaveContests(predicate = Contest::isCurrent)
        } finally {
            mutex.unlock()
        }
    }

    override fun getPastContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            loadAndSaveContests(sortedByDescending = true, predicate = Contest::isPast)
        } finally {
            mutex.unlock()
        }
    }

    override fun getContestStandings(contestId: Long): Deferred<ContestInfo> = async {
        mutex.lock()
        try {
            serviceApi.getContestStandings(contestId, 100).await().result // TODO: После добавления экрана настроек получать сколько загружать вместо 100
        } finally {
            mutex.unlock()
        }
    }

    override fun getUsersInfo(handlers: List<String>): Deferred<List<User>> = async {
        mutex.lock()
        try {
            serviceApi.getUsersInfo(handlers.joinToString(separator = ";")).await().result
        } finally {
            mutex.unlock()
        }
    }

    private suspend fun loadAndSaveContests(sortedByDescending: Boolean = false,
                                            predicate: (contest: Contest) -> Boolean): List<Contest> {
        val contests = dataBaseManager.loadAllContests().await()

        if (!contests.isEmpty()) {
            val lastLoadedTime = preferencesManager.getLong(LAST_LOADED_TIME_CONTESTS_KEY, 0)
            if (Date().time - lastLoadedTime < ONE_HOUR) {
                return if (sortedByDescending) {
                    contests.filter(predicate).sortedByDescending { it.startTimeSeconds }
                } else {
                    contests.filter(predicate).sortedBy { it.startTimeSeconds }
                }
            }
        }

        val contestsResponse = serviceApi.getContestList().await()
        if (contestsResponse.status == "FAILED") {
            return emptyList()
        }

        val sortedContestsList = if (sortedByDescending) {
            contestsResponse.result.sortedByDescending { it.startTimeSeconds }
        } else {
            contestsResponse.result.sortedBy { it.startTimeSeconds }
        }
        dataBaseManager.saveAllContests(sortedContestsList).await()
        preferencesManager.putLong(LAST_LOADED_TIME_CONTESTS_KEY, Date().time)
        return sortedContestsList.filter(predicate)
    }

    companion object {
        private const val LAST_LOADED_TIME_CONTESTS_KEY = "last_loaded_time_contests"
        private val ONE_HOUR = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    }
}