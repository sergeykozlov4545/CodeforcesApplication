package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.Contest
import com.example.sergey.codeforcesapplication.model.database.DataBaseManager
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManager
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.sync.Mutex

interface ContestsRepository {
    fun getUncommingContests(): Deferred<List<Contest>>
    fun getCurrentContests(): Deferred<List<Contest>>
    fun getPastContests(): Deferred<List<Contest>>
    fun saveContests(contests: List<Contest>): Deferred<Unit>
}

class ContestsRepositoryImpl(
        // TODO: Добавить ServiceApi
        private val dataBaseManager: DataBaseManager,
        private val PreferencesManager: PreferencesManager
) : ContestsRepository {
    private val mutex = Mutex()

    override fun getUncommingContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            dataBaseManager.getUncommingContests().await()
        } finally {
            mutex.unlock()
        }
    }

    override fun getCurrentContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            dataBaseManager.getCurrentContests().await()
        } finally {
            mutex.unlock()
        }
    }

    override fun getPastContests(): Deferred<List<Contest>> = async {
        mutex.lock()
        try {
            dataBaseManager.getPastContests().await()
        } finally {
            mutex.unlock()
        }
    }

    override fun saveContests(contests: List<Contest>): Deferred<Unit> = async {
        mutex.lock()
        try {
            dataBaseManager.saveAllContests(contests).await()
        } finally {
            mutex.unlock()
        }
    }
}