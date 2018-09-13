package com.example.sergey.codeforcesapplication.feature.userInfo.fragment

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object UserInfoPresenterFactory {
    fun create(context: Context) = UserInfoPresenterImpl(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}