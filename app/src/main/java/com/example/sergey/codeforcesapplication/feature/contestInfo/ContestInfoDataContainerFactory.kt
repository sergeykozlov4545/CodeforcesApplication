package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList.ProblemListAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Problem

object ProblemsListDataContainerFactory {
    fun create(parent: ViewGroup) = ProcessingListDataContainerImpl<Problem>(parent).apply {
        setAdapter(ProblemListAdapter())
    }
}