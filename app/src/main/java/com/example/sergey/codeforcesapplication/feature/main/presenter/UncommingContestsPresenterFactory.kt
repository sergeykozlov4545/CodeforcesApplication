package com.example.sergey.codeforcesapplication.feature.main.presenter

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object UncommingContestsPresenterFactory {
    fun create(context: Context) = UncommingContestsFragmentPresenter(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}