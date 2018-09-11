package com.example.sergey.codeforcesapplication.feature.main.activityFactory

import android.content.Context
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.main.ContestsFragment
import com.example.sergey.codeforcesapplication.feature.main.adapter.ContestsAdapter
import com.example.sergey.codeforcesapplication.feature.main.presenter.CurrentContestsPresenterFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.PastContestsPresenterFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.UncommingContestsPresenterFactory
import com.example.sergey.codeforcesapplication.model.pojo.Contest

object MainActivityFactory {
    fun create(context: Context) = arrayListOf(
            UncommingContestsFragmentFactory.create(context),
            CurrentContestsFragmentFactory.create(context),
            PastContestsFragmentFactory.create(context)
    )
}

object UncommingContestsFragmentFactory {
    fun create(context: Context): FragmentInfo = FragmentInfo(
            fragment = ContestsFragment.getInstance(
                    presenter = UncommingContestsPresenterFactory.create(context)),
            fragmentTitle = context.getString(R.string.upcomingContests)
    )
}

object CurrentContestsFragmentFactory {
    fun create(context: Context): FragmentInfo = FragmentInfo(
            fragment = ContestsFragment.getInstance(
                    presenter = CurrentContestsPresenterFactory.create(context)),
            fragmentTitle = context.getString(R.string.currentContests)
    )
}

object PastContestsFragmentFactory {
    fun create(context: Context): FragmentInfo = FragmentInfo(
            fragment = ContestsFragment.getInstance(
                    presenter = PastContestsPresenterFactory.create(context)),
            fragmentTitle = context.getString(R.string.pastContests)
    )
}

object ContestsDataContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<Contest>(parent).apply {
        setAdapter(ContestsAdapter())
    }
}