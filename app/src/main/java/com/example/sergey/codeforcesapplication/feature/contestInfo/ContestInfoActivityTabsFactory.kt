package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList.ProblemsListFragment
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList.ContestStandingsFragment
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.TabInfo

object ContestInfoActivityTabsFactory {
    fun create(context: Context, contestId: Long) = arrayListOf(
            ProblemsTabFactory.create(context, contestId),
            StandingsTabFactory.create(context, contestId)
    )
}

object ProblemsTabFactory {
    fun create(context: Context, contestId: Long) = TabInfo(
            tabTitle = context.getString(R.string.problems),
            fragment = ProblemsListFragment.create(context, contestId)
    )
}

object StandingsTabFactory {
    fun create(context: Context, contestId: Long) = TabInfo(
            tabTitle = context.getString(R.string.standings),
            fragment = ContestStandingsFragment.create(context, contestId)
    )
}

