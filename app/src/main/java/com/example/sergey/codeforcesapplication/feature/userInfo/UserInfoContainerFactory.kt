package com.example.sergey.codeforcesapplication.feature.userInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.feature.ratingInfo.RatingInfoActivity

object UserInfoContainerFactory {
    fun create(context: Context) = UserInfoContainer {
        RatingInfoActivity.start(context, it.handle)
    }
}