package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestStandingsContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface ContestStandingsFragmentView : ProcessingListView<RankListRow> {
    fun getContestId(): Long
}

class ContestStandingsFragment :
        ProcessingListFragment<RankListRow, ContestStandingsFragmentView>(), ContestStandingsFragmentView {

    private var contestId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contestId = arguments?.getLong(CONTEST_ID_EXTRA, -1) ?: -2
    }

    override val processingContainer by lazy {
        val processingContainerView = view!!.findViewById<ViewGroup>(R.id.processingContainer)
        ContestStandingsContainerFactory.create(processingContainerView)
    }

    override val presenter by lazy { ContestStandingsPresenterFactory.create(context!!) }

    override fun getContestId() = contestId

    companion object {
        private const val CONTEST_ID_EXTRA = "contest_id"

        fun create(context: Context, contestId: Long) = ContestStandingsFragment().apply {
            arguments = getArguments(context, contestId)
        }

        // TODO: использовать KTX
        private fun getArguments(context: Context, contestId: Long) = Bundle().apply {
            putLong(CONTEST_ID_EXTRA, contestId)
            putInt(ProcessingListDataContainerImpl.BACKGROUND_COLOR_EXTRA, android.R.color.white)
            putBoolean(ProcessingListDataContainerImpl.VISIBLE_DIVIDERS_EXTRA, true)
        }
    }
}