package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.remote.ServiceApi

object ContestsRepositoryFactory {
    fun create(serviceApi: ServiceApi): ContestsRepository {
        return ContestsRepositoryImpl(serviceApi)
    }
}