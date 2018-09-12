package com.example.sergey.codeforcesapplication.model.cache

import java.util.*

data class CacheObject<T>(val value: T? = null, private val timeToLiveMillis: Long? = null) {
    private val createdMillis = Date().time

    val deadMillis: Long?
        get() = if (timeToLiveMillis == null) null else createdMillis + timeToLiveMillis
}