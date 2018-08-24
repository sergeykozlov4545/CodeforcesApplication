package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.database.DataBaseManager
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManager
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi

object ContestsRepositoryFactory {

    fun create(serviceApi: ServiceApi,
               dataBaseManager: DataBaseManager,
               preferenceManager: PreferencesManager): ContestsRepository {
        return ContestsRepositoryImpl(serviceApi, dataBaseManager, preferenceManager)
    }
}