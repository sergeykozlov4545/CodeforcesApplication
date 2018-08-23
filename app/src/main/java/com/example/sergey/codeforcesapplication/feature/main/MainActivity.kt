package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.model.Contest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), MainContractor.MainActivityView {

    private val presenter = MainActivityPresenterImpl()

    private var contestsListAdapter = ContestsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
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

    override fun showContests(contestsList: List<Contest>) {
        contestsListAdapter.updateData(contestsList)
    }

    override fun showEmptyListMessage() {
        TODO("not implemented")
    }

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

        with(contestsList) {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = contestsListAdapter
        }
    }
}
