package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.feature.base.ViewWithProccesing
import com.example.sergey.codeforcesapplication.feature.base.WithProcessingFragment
import com.example.sergey.codeforcesapplication.feature.contestInfo.activity.ContestInfoActivity
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface ContestStandingsView : ViewWithProccesing<RankListRow> {
    fun getContestId(): Long
}

class ContestStandingsFragment : WithProcessingFragment<RankListRow>(), ContestStandingsView {

    private val contestStandingsAdapter = ContestStandingsAdapter()

    private var contestId: Long = 0

    private lateinit var presenter: ContestStandingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        restoreData()
        initPresenter()
    }

    override fun onResume() {
        super.onResume()

        setDataListBackground(ContextCompat.getColor(context!!, android.R.color.white))

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun onPause() {
        super.onPause()

        presenter.detachView()
    }

    override fun getEmptyListMessageText() = getString(R.string.standingsListIsEmpty)

    override fun getDataListLayoutManager() = LinearLayoutManager(context?.applicationContext)

    override fun getDataListAdapter() = contestStandingsAdapter

    override fun getContestId() = contestId

    private fun restoreData() {
        val localArguments = arguments ?: return
        contestId = localArguments.getLong(ContestInfoActivity.CONTEST_ID_EXTRA, 0)
    }

    private fun initPresenter() {
        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = ContestStandingsPresenterImpl(repository)
    }
}