package com.example.sergey.codeforcesapplication.application

import android.app.Application
import com.example.sergey.codeforcesapplication.model.database.DataBaseManagerImpl
import com.example.sergey.codeforcesapplication.model.preferences.PreferencesManagerImpl
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepositoryFactory

class CodeforcesApplication : Application() {

    lateinit var contestsRepository: ContestsRepository

    override fun onCreate() {
        super.onCreate()

        val dataBaseManager = DataBaseManagerImpl(applicationContext)
        val preferencesManager = PreferencesManagerImpl(applicationContext)
        contestsRepository = ContestsRepositoryFactory.create(dataBaseManager, preferencesManager)

    }
}