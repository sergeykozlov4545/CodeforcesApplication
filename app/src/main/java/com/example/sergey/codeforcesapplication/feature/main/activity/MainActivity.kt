package com.example.sergey.codeforcesapplication.feature.main.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.contestInfo.ContestInfoActivity
import com.example.sergey.codeforcesapplication.feature.main.ContestsListAdapter
import com.example.sergey.codeforcesapplication.feature.main.fragment.CurrentContestsListFragment
import com.example.sergey.codeforcesapplication.feature.main.fragment.PastContestsListFragment
import com.example.sergey.codeforcesapplication.feature.main.fragment.UncommingContestsListFragment
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), MainActivityContractor.View {

    private val presenter = MainActivityPresenterImpl()

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

    override fun getPresenter(): MainActivityContractor.Presenter<MainActivityContractor.View> {
        return presenter
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    override fun showUncommingContests() = showFragment(UncommingContestsListFragment())
    override fun showCurrentContests() = showFragment(CurrentContestsListFragment())
    override fun showPastContests() = showFragment(PastContestsListFragment())

    override fun showContestInfoActivity(contest: Contest) {
        startActivity(Intent(this, ContestInfoActivity::class.java).apply {
            putExtra(ContestInfoActivity.CONTEST_ID_EXTRA, contest.id)
            putExtra(ContestInfoActivity.CONTEST_NAME_EXTRA, contest.name)
        })
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, fragment)
        }.commitAllowingStateLoss()
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
    }
}
