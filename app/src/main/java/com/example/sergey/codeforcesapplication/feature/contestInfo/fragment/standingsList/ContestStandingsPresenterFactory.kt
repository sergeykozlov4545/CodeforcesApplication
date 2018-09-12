package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object ContestStandingsPresenterFactory {
    fun create(context: Context) = ContestStandingsPresenterImpl(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}