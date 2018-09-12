package com.example.sergey.codeforcesapplication.model.cache

import android.util.LruCache
import java.util.*
import java.util.concurrent.TimeUnit

interface CacheManager {
    fun <T> putValue(key: CacheObjectKey, value: T, timeToLiveMillis: Long? = null)
    fun getValue(key: CacheObjectKey): Any?
    fun remove(key: CacheObjectKey)
}

object CacheManagerImpl : CacheManager {
    private val CACHE_SIZE = CacheObjectKey.values().size

    private val memCache: LruCache<CacheObjectKey, CacheObject<out Any?>> = LruCache(CACHE_SIZE)

    init {
        Thread { work() }.apply {
            name = "cleaner"
            isDaemon = true
        }.start()
    }

    override fun <T> putValue(key: CacheObjectKey, value: T, timeToLiveMillis: Long?) {
        memCache.put(key, CacheObject(value, timeToLiveMillis))
    }

    override fun getValue(key: CacheObjectKey): Any? {
        val tmp = memCache.get(key) ?: return null
        return tmp.value
    }

    override fun remove(key: CacheObjectKey) {
        memCache.remove(key)
    }

    private fun work() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1)
            } catch (e: InterruptedException) {

            }
            cleanCache()
        }
    }

    private fun cleanCache() {
        val currentMillis = Date().time
        memCache.snapshot().forEach { entry ->
            val deadMillis = entry.value.deadMillis ?: Long.MAX_VALUE
            if (currentMillis >= deadMillis) remove(entry.key)
        }
    }
}