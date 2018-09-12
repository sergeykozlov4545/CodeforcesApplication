package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList.ProblemsListFragment
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.FragmentInfo

object ContestInfoActivityFactory {
    fun create(context: Context, contestId: Long) = arrayListOf(
            ProblemsListFragmentFactory.create(context, contestId)
    )
}

object ProblemsListFragmentFactory {
    fun create(context: Context, contestId: Long) = FragmentInfo(
            fragment = ProblemsListFragment.create(context, contestId),
            fragmentTitle = context.getString(R.string.problems)
    )
}