package com.example.sergey.codeforcesapplication.feature.main.activityFactory

import android.content.Context
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.main.CurrentContestsFragment
import com.example.sergey.codeforcesapplication.feature.main.PastContestsFragment
import com.example.sergey.codeforcesapplication.feature.main.UpcommingContestsFragment

object MainActivityFactory {
    fun create(context: Context) = arrayListOf(
            UpcommingContestsFragmentFactory.create(context),
            CurrentContestsFragmentFactory.create(context),
            PastContestsFragmentFactory.create(context)
    )
}

object UpcommingContestsFragmentFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = UpcommingContestsFragment.create(context),
            fragmentTitle = context.getString(R.string.upcomingContests)
    )
}

object CurrentContestsFragmentFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = CurrentContestsFragment.create(context),
            fragmentTitle = context.getString(R.string.currentContests)
    )
}

object PastContestsFragmentFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = PastContestsFragment.create(context),
            fragmentTitle = context.getString(R.string.pastContests)
    )
}