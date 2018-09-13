package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.content.Context
import android.os.Bundle
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestStandingsContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface ContestStandingsFragmentView : ProcessingListView<RankListRow> {
    // TODO: Попробовать использовать свойство вместо этой функции
    fun getContestId(): Long
}

class ContestStandingsFragment :
        ProcessingListFragment<RankListRow, ContestStandingsFragmentView>(), ContestStandingsFragmentView {

    private var contestId: Long = 0
    override val processingContainer by lazy { ContestStandingsContainerFactory.create(context!!) }
    override val presenter by lazy { ContestStandingsPresenterFactory.create(context!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contestId = arguments?.getLong(CONTEST_ID_EXTRA, -1) ?: -2
    }

    override fun getContestId() = contestId

    companion object {
        private const val CONTEST_ID_EXTRA = "contest_id"

        fun create(context: Context, contestId: Long) = ContestStandingsFragment().apply {
            arguments = getArguments(context, contestId)
        }

        // TODO: использовать KTX
        private fun getArguments(context: Context, contestId: Long) = Bundle().apply {
            putInt(ProcessingListDataContainerImpl.BACKGROUND_COLOR_EXTRA, android.R.color.white)
            putBoolean(ProcessingListDataContainerImpl.VISIBLE_DIVIDERS_EXTRA, true)
            putLong(CONTEST_ID_EXTRA, contestId)
        }
    }
}