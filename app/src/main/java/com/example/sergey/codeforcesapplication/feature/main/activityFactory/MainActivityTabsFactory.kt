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
    fun create(context: Context) = FragmentInfo(
            fragment = UpcommingContestsFragment.create(context),
            fragmentTitle = context.getString(R.string.upcomingContests)
    )
}

object CurrentContestsTabFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = CurrentContestsFragment.create(context),
            fragmentTitle = context.getString(R.string.currentContests)
    )
}

object PastContestsTabFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = PastContestsFragment.create(context),
            fragmentTitle = context.getString(R.string.pastContests)
    )
}