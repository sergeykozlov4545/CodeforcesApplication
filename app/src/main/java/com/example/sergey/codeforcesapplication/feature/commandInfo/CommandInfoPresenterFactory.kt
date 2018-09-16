package com.example.sergey.codeforcesapplication.feature.commandInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object CommandInfoPresenterFactory {
    fun create(context: Context) = CommandInfoPresenterImpl(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}