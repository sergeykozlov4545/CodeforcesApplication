package com.example.sergey.codeforcesapplication.feature.main.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.feature.base.WithProcessingFragment
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapter
import com.example.sergey.codeforcesapplication.feature.main.ContestsListAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Contest

class UncommingContestsListFragment : WithProcessingFragment<Contest>(), ContestsListFragmentView {

    private val contestsListAdapter = ContestsListAdapter()
    private lateinit var presenter: ContestsListFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = UncommingContestsListFragmentPresenter(repository)
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

    override fun getEmptyListMessageText(): String = getString(R.string.emptyUncommingContestsList)

    override fun getDataListLayoutManager(): RecyclerView.LayoutManager {
        val columnCount = resources.getInteger(R.integer.list_column_count)
        return GridLayoutManager(context, columnCount)
    }

    override fun getDataListAdapter(): DataListAdapter<Contest> = contestsListAdapter
}