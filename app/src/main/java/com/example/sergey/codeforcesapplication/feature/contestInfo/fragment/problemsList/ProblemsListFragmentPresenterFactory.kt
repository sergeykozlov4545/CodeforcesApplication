package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object ProblemsListFragmentPresenterFactory {
    fun create(context: Context) = ProblemsListFragmentPresenterImpl(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}