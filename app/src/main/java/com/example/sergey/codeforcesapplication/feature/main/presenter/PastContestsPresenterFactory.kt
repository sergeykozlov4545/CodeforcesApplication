package com.example.sergey.codeforcesapplication.feature.main.presenter

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object PastContestsPresenterFactory {
    fun create(context: Context) = PastContestsFragmentPresenter(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}