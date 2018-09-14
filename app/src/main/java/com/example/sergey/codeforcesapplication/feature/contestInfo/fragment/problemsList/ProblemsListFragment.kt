package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.content.Context
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.COUNT_COLUMNS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestInfoActivity.Companion.CONTEST_ID_EXTRA
import com.example.sergey.codeforcesapplication.feature.contestInfo.ProblemsContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.Problem

interface ProblemsListFragmentView : ProcessingListView<Problem> {
    val contestId: Long
}

class ProblemsListFragment :
        ProcessingListFragment<Problem, ProblemsListFragmentView>(), ProblemsListFragmentView {

    override val processingContainer by lazy { ProblemsContainerFactory.create() }
    override val presenter by lazy { ProblemsListFragmentPresenterFactory.create(context!!) }

    override val contestId by lazy { arguments?.getLong(CONTEST_ID_EXTRA, -1) ?: -2 }

    companion object {
        fun create(context: Context, contestId: Long) = ProblemsListFragment().apply {
            // TODO: использовать KTX
            arguments = Bundle().apply {
                val resources = context.resources
                putInt(COUNT_COLUMNS_EXTRA, resources.getInteger(R.integer.list_column_count))
                putLong(CONTEST_ID_EXTRA, contestId)
            }
        }
    }
}