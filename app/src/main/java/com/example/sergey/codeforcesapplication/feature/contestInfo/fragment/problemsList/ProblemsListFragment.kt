package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.os.Bundle
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.contestInfo.ProblemsListDataContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.Problem

interface ProblemsListFragmentView : ProcessingListView<Problem> {
    fun getContestId(): Long
}

class ProblemsListFragment :
        ProcessingListFragment<Problem, ProblemsListFragmentView>(), ProblemsListFragmentView {

    private var contestId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contestId = arguments?.getLong(CONTEST_ID_EXTRA, -1) ?: -2
    }

    override val processingContainer by lazy {
        val processingContainerView = view!!.findViewById<ViewGroup>(R.id.processingContainer)
        ProblemsListDataContainerFactory.create(processingContainerView)
    }

    override val presenter by lazy { ProblemsListFragmentPresenterFactory.create(context!!) }

    override fun getContestId() = contestId

    companion object {
        private const val CONTEST_ID_EXTRA = "contest_id"

        fun create(contestId: Long) = ProblemsListFragment().apply {
            arguments = getArguments(contestId)
        }

        private fun getArguments(contestId: Long) = Bundle().apply {
            putLong(CONTEST_ID_EXTRA, contestId)
        }
    }
}