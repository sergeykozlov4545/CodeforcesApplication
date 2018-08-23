package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.database.DataBaseManager
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManager

object ContestsRepositoryFactory {

    fun create(dataBaseManager: DataBaseManager,
               preferenceManager: PreferencesManager): ContestsRepository {
        return ContestsRepositoryImpl(dataBaseManager, preferenceManager)
    }
}