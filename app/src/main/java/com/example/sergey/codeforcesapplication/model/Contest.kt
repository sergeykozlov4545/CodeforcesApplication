package com.example.sergey.codeforcesapplication.model

data class Contest(
        val id: Int,
        val name: String,
        val startTimeSeconds: Long?,
        val durationSeconds: Long
)