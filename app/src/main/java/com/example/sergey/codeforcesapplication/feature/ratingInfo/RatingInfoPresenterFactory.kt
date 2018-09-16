package com.example.sergey.codeforcesapplication.feature.ratingInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication

object RatingInfoPresenterFactory {
    fun create(context: Context) = RatingInfoPresenterImpl(
            (context.applicationContext as CodeforcesApplication).contestsRepository
    )
}