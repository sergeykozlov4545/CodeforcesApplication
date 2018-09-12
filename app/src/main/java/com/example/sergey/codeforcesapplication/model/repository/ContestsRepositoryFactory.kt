package com.example.sergey.codeforcesapplication.model.repository

import com.example.sergey.codeforcesapplication.model.cache.CacheManager
import com.example.sergey.codeforcesapplication.model.remote.ServiceApi

object ContestsRepositoryFactory {
    fun create(serviceApi: ServiceApi, cacheManager: CacheManager): ContestsRepository {
        return ContestsRepositoryImpl(serviceApi, cacheManager)
    }
}