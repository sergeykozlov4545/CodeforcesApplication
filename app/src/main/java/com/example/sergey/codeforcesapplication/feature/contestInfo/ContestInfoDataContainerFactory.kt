package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList.ProblemListAdapter
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList.ContestStandingsAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Problem
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

object ProblemsContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<Problem>(parent).apply {
        setAdapter(ProblemListAdapter())
    }
}

object ContestStandingsContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<RankListRow>(parent).apply {
        setAdapter(ContestStandingsAdapter())
    }
}