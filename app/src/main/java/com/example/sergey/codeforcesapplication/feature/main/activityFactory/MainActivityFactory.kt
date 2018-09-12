package com.example.sergey.codeforcesapplication.feature.main.activityFactory

import android.content.Context
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
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
            fragment = UpcommingContestsFragment().apply {
                // TODO: использовать KTX
                arguments = Bundle().apply {
                    val columnCount = context.resources.getInteger(R.integer.list_column_count)
                    putInt(ProcessingListDataContainerImpl.COUNT_COLUMNS_EXTRA, columnCount)
                }
            },
            fragmentTitle = context.getString(R.string.upcomingContests)
    )
}

object CurrentContestsFragmentFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = CurrentContestsFragment().apply {
                // TODO: использовать KTX
                arguments = Bundle().apply {
                    val columnCount = context.resources.getInteger(R.integer.list_column_count)
                    putInt(ProcessingListDataContainerImpl.COUNT_COLUMNS_EXTRA, columnCount)
                }
            },
            fragmentTitle = context.getString(R.string.currentContests)
    )
}

object PastContestsFragmentFactory {
    fun create(context: Context) = FragmentInfo(
            fragment = PastContestsFragment().apply {
                // TODO: использовать KTX
                arguments = Bundle().apply {
                    val columnCount = context.resources.getInteger(R.integer.list_column_count)
                    putInt(ProcessingListDataContainerImpl.COUNT_COLUMNS_EXTRA, columnCount)
                }
            },
            fragmentTitle = context.getString(R.string.pastContests)
    )
}