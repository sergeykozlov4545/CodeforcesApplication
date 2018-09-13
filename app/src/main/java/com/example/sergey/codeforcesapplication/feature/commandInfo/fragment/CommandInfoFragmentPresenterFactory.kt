package com.example.sergey.codeforcesapplication.feature.commandInfo.fragment

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object CommandInfoFragmentPresenterFactory {
    fun create(context: Context) = CommandInfoFragmentPresenterImpl(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}