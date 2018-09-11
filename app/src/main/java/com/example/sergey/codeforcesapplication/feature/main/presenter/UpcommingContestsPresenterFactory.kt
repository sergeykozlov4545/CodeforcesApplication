package com.example.sergey.codeforcesapplication.feature.main.presenter

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object UpcommingContestsPresenterFactory {
    fun create(context: Context) = UpcommingContestsFragmentPresenter(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}