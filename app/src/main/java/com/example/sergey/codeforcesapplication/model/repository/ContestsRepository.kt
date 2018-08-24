package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.Contest
import com.example.sergey.codeforcesapplication.model.database.DataBaseManager
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManager
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.sync.Mutex

interface ContestsRepository {
    fun getUncommingContests(): Deferred<List<Contest>>
    fun getCurrentContests(): Deferred<List<Contest>>
    fun getPastContests(): Deferred<List<Contest>>
}

class ContestsRepositoryImpl(
        private val serviceApi: ServiceApi,
        private val dataBaseManager: DataBaseManager,
        private val PreferencesManager: PreferencesManager
) : ContestsRepository {
    private val mutex = Mutex()

    override fun getUncommingContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            loadAndSaveContests { it == "BEFORE" }
        } finally {
            mutex.unlock()
        }
    }

    override fun getCurrentContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            loadAndSaveContests { it == "CODING" }
        } finally {
            mutex.unlock()
        }
    }

    override fun getPastContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            loadAndSaveContests(true) { it in arrayOf("PENDING_SYSTEM_TEST", "SYSTEM_TEST", "FINISHED") }
        } finally {
            mutex.unlock()
        }
    }

    private suspend fun loadAndSaveContests(sortedByDescending: Boolean = false,
                                            predicate: (phaseContest: String) -> Boolean): List<Contest> {
        val contests = dataBaseManager.loadAllContests().await()

        if (!contests.isEmpty()) {
            // TODO: Тут проверать на время жизни в базе. Если > 1дня, то перелоад из сети

            return if (sortedByDescending) {
                contests.filter { predicate(it.phase) }.sortedByDescending { it.startTimeSeconds }
            } else {
                contests.filter { predicate(it.phase) }.sortedBy { it.startTimeSeconds }
            }
        }

        val contestsResponse = serviceApi.getContestsList().await()
        if (contestsResponse.status == "FAILED") {
            return emptyList()
        }

        val sortedContestsList = if (sortedByDescending) {
            contestsResponse.result.sortedByDescending { it.startTimeSeconds }
        } else {
            contestsResponse.result.sortedBy { it.startTimeSeconds }
        }
        dataBaseManager.saveAllContests(sortedContestsList).await()
        return sortedContestsList.filter { predicate(it.phase) }
    }
}