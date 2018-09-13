package com.example.sergey.codeforcesapplication.feature.main

import android.content.Context
import android.widget.Toast
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestInfoActivity
import com.example.sergey.codeforcesapplication.feature.main.adapter.ContestsAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Contest

object UpcommingContestsContainerFactory {
    fun create(context: Context) = ProcessingListDataContainerImpl<Contest>().apply {
        setAdapter(ContestsAdapter {
            // TODO: Использовать KTX
            Toast.makeText(context, R.string.contest_is_not_started, Toast.LENGTH_SHORT).show()
        })
    }
}

object CurrentContestsContainerFactory {
    fun create(context: Context) = ProcessingListDataContainerImpl<Contest>().apply {
        setAdapter(ContestsAdapter { contest -> ContestInfoActivity.start(context, contest) })
    }
}

object PastContestsContainerFactory {
    fun create(context: Context) = ProcessingListDataContainerImpl<Contest>().apply {
        setAdapter(ContestsAdapter { contest -> ContestInfoActivity.start(context, contest) })
    }
}