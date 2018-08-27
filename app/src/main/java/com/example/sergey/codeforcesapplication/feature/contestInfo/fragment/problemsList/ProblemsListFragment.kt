package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.feature.base.ViewWithProccesing
import com.example.sergey.codeforcesapplication.feature.base.WithProcessingFragment
import com.example.sergey.codeforcesapplication.feature.contestInfo.activity.ContestInfoActivity.Companion.CONTEST_ID_EXTRA
import com.example.sergey.codeforcesapplication.model.pojo.Problem

interface ProblemsListFragmentView : ViewWithProccesing<Problem> {
    fun getContestId(): Long
}

class ProblemsListFragment : WithProcessingFragment<Problem>(), ProblemsListFragmentView {

    private val profileListAdapter = ProblemListAdapter()

    private lateinit var presenter: ProblemsListFragmentPresenter

    private var contestId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        restoreData()
        initPresenter()
    }

    override fun onResume() {
        super.onResume()

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun onPause() {
        super.onPause()

        presenter.detachView()
    }

    override fun getEmptyListMessageText(): String = getString(R.string.problemsListIsEmpty)

    override fun getDataListLayoutManager(): RecyclerView.LayoutManager {
        val columnCount = resources.getInteger(R.integer.list_column_count)
        return GridLayoutManager(context, columnCount)
    }

    override fun getDataListAdapter() = profileListAdapter

    override fun getContestId() = contestId

    private fun restoreData() {
        val localArguments = arguments ?: return
        contestId = localArguments.getLong(CONTEST_ID_EXTRA, 0)
    }

    private fun initPresenter() {
        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = ProblemsListFragmentPresenter(repository)
    }
}