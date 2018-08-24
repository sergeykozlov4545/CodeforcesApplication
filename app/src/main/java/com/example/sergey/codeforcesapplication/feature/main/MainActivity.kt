package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.extansion.hide
import com.example.sergey.codeforcesapplication.extansion.show
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), MainActivityContractor.MainActivityView {

    private lateinit var presenter: MainActivityContractor.MainActivityPresenter<MainActivityContractor.MainActivityView>

    private var contestsListAdapter = ContestsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
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

    override fun getPresenter() = presenter

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showContests(contestsList: List<Contest>) {
        contestsListAdapter.updateData(contestsList)
        contestsListView.show()
    }

    override fun hideContests() = contestsListView.hide()

    override fun showEmptyListMessage() = emptyListView.show()

    override fun hideEmptyListMessage() = emptyListView.hide()

    private fun initView() {
        (toolbar.findViewById<TextView>(R.id.title_toolbar)).setText(R.string.contestsList)

        tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    ContestsListAdapter.UNCOMMING_CONTESTS -> presenter.uncommingContestsTabClicked()
                    ContestsListAdapter.CURRENT_CONTESTS -> presenter.currentContestsTabClicked()
                    ContestsListAdapter.PAST_CONTESTS -> presenter.pastContestsTabClicked()
                    else -> {
                    }
                }
            }
        })

        with(contestsListView) {
            val columnCount = resources.getInteger(R.integer.contests_list_column_count)
            layoutManager = GridLayoutManager(applicationContext, columnCount)
            adapter = contestsListAdapter
        }
    }

    private fun initPresenter() {
        presenter = MainActivityPresenterImpl((applicationContext as CodeforcesApplication).contestsRepository)
    }
}
