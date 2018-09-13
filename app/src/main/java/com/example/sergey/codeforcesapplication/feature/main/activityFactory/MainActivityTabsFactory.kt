package com.example.sergey.codeforcesapplication.feature.main.activityFactory

import android.content.Context
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.main.CurrentContestsFragment
import com.example.sergey.codeforcesapplication.feature.main.PastContestsFragment
import com.example.sergey.codeforcesapplication.feature.main.UpcommingContestsFragment

object MainActivityTabsFactory {
    fun create(context: Context) = arrayListOf(
            UpcommingContestsTabFactory.create(context),
            CurrentContestsTabFactory.create(context),
            PastContestsTabFactory.create(context)
    )
}

object UpcommingContestsTabFactory {
    fun create(context: Context) = TabInfo(
            tabTitle = context.getString(R.string.upcomingContests),
            fragment = UpcommingContestsFragment.create(context)
    )
}

object CurrentContestsTabFactory {
    fun create(context: Context) = TabInfo(
            tabTitle = context.getString(R.string.currentContests),
            fragment = CurrentContestsFragment.create(context)
    )
}

object PastContestsTabFactory {
    fun create(context: Context) = TabInfo(
            tabTitle = context.getString(R.string.pastContests),
            fragment = PastContestsFragment.create(context)
    )
}