package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.content.Context
import android.os.Bundle
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.BACKGROUND_COLOR_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.VISIBLE_DIVIDERS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestInfoActivity.Companion.CONTEST_ID_EXTRA
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestStandingsContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface ContestStandingsFragmentView : ProcessingListView<RankListRow> {
    val contestId: Long
}

class ContestStandingsFragment :
        ProcessingListFragment<RankListRow, ContestStandingsFragmentView>(), ContestStandingsFragmentView {

    override val processingContainer by lazy { ContestStandingsContainerFactory.create(context!!) }
    override val presenter by lazy { ContestStandingsPresenterFactory.create(context!!) }

    override val contestId by lazy { arguments?.getLong(CONTEST_ID_EXTRA, -1) ?: -2 }

    companion object {
        fun create(context: Context, contestId: Long) = ContestStandingsFragment().apply {
            // TODO: использовать KTX
            arguments = Bundle().apply {
                putInt(BACKGROUND_COLOR_EXTRA, android.R.color.white)
                putBoolean(VISIBLE_DIVIDERS_EXTRA, true)
                putLong(CONTEST_ID_EXTRA, contestId)
            }
        }
    }
}