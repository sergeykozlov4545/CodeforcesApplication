package com.example.sergey.codeforcesapplication.model.preferences

import android.content.Context

interface PreferencesManager {
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String): String

    fun putLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long): Long
}

class PreferencesManagerImpl(context: Context) : PreferencesManager {

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun putString(key: String, value: String) =
            preferences.edit().putString(key, value).apply()

    override fun getString(key: String, defaultValue: String) =
            preferences.getString(key, defaultValue)!!

    override fun putLong(key: String, value: Long) =
            preferences.edit().putLong(key, value).apply()

    override fun getLong(key: String, defaultValue: Long) =
            preferences.getLong(key, defaultValue)

    companion object {
        private const val PREFERENCES_NAME = "preferences_manager"
    }
}