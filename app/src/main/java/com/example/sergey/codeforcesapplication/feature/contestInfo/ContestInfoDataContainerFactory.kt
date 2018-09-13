package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.commandInfo.CommandInfoActivity
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList.ProblemListAdapter
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList.ContestStandingsAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Problem
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

object ProblemsContainerFactory {
    fun create() = ProcessingListDataContainerImpl<Problem>().apply {
        setAdapter(ProblemListAdapter())
    }
}

object ContestStandingsContainerFactory {
    fun create(context: Context) = ProcessingListDataContainerImpl<RankListRow>().apply {
        setAdapter(ContestStandingsAdapter { rankRow ->
            CommandInfoActivity.start(context, rankRow)
        })
    }
}