package com.example.sergey.codeforcesapplication.feature.main

import android.view.ViewGroup
import android.widget.Toast
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestInfoActivity
import com.example.sergey.codeforcesapplication.feature.main.adapter.ContestsAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Contest

object UpcommingContestsContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<Contest>(parent).apply {
        setAdapter(ContestsAdapter {
            // TODO: Использовать KTX
            Toast.makeText(parent.context, R.string.contest_is_not_started, Toast.LENGTH_SHORT).show()
        })
    }
}

object CurrentContestsContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<Contest>(parent).apply {
        setAdapter(
                ContestsAdapter { context -> ContestInfoActivity.start(parent.context, context) }
        )
    }
}

object PastContestsContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<Contest>(parent).apply {
        setAdapter(
                ContestsAdapter { context -> ContestInfoActivity.start(parent.context, context) }
        )
    }
}