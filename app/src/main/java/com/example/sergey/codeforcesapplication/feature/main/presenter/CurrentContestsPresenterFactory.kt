package com.example.sergey.codeforcesapplication.feature.main.presenter

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object CurrentContestsPresenterFactory {
    fun create(context: Context) = CurrentContestsFragmentPresenter(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}