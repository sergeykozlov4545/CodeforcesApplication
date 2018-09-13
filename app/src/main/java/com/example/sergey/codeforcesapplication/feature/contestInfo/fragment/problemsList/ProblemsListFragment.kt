package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.content.Context
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.contestInfo.ProblemsContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.Problem

interface ProblemsListFragmentView : ProcessingListView<Problem> {
    // TODO: Попробовать использовать свойство вместо этой функции
    fun getContestId(): Long
}

class ProblemsListFragment :
        ProcessingListFragment<Problem, ProblemsListFragmentView>(), ProblemsListFragmentView {

    private var contestId: Long = 0
    override val processingContainer by lazy { ProblemsContainerFactory.create() }
    override val presenter by lazy { ProblemsListFragmentPresenterFactory.create(context!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contestId = arguments?.getLong(CONTEST_ID_EXTRA, -1) ?: -2
    }

    override fun getContestId() = contestId

    companion object {
        private const val CONTEST_ID_EXTRA = "contest_id"

        fun create(context: Context, contestId: Long) = ProblemsListFragment().apply {
            arguments = getArguments(context, contestId)
        }

        // TODO: использовать KTX
        private fun getArguments(context: Context, contestId: Long) = Bundle().apply {
            putInt(ProcessingListDataContainerImpl.COUNT_COLUMNS_EXTRA,
                    context.resources.getInteger(R.integer.list_column_count))
            putLong(CONTEST_ID_EXTRA, contestId)
        }
    }
}