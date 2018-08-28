package com.example.sergey.codeforcesapplication.feature.main.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.feature.contestInfo.activity.ContestInfoActivity
import com.example.sergey.codeforcesapplication.feature.main.activity.MainActivityPresenterImpl.Companion.CURRENT_CONTESTS
import com.example.sergey.codeforcesapplication.feature.main.activity.MainActivityPresenterImpl.Companion.PAST_CONTESTS
import com.example.sergey.codeforcesapplication.feature.main.activity.MainActivityPresenterImpl.Companion.UNCOMMING_CONTESTS
import com.example.sergey.codeforcesapplication.feature.main.fragment.CurrentContestsListFragment
import com.example.sergey.codeforcesapplication.feature.main.fragment.PastContestsListFragment
import com.example.sergey.codeforcesapplication.feature.main.fragment.UncommingContestsListFragment
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

interface MainActivityView : MVPView {
    fun getPresenter(): MainActivityPresenter
    fun showMessage(messageId: Int)
    fun showUncommingContests()
    fun showCurrentContests()
    fun showPastContests()
    fun showContestInfoActivity(contest: Contest)
    fun selectTab(tabPosition: Int)
}

class MainActivity : AppCompatActivity(), MainActivityView {

    private val presenter = MainActivityPresenterImpl()

    private var tabPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restoreData(savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()

        presenter.attachView(this)
        if (tabPosition == 0) {
            presenter.viewIsReady()
        } else {
            presenter.restoredView(tabPosition)
        }
    }

    override fun onPause() {
        super.onPause()

        presenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putInt(TAB_POSITION_EXTRA, tabPosition)
        }
    }

    override fun getPresenter(): MainActivityPresenter {
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

    override fun selectTab(tabPosition: Int) {
        tabsLayout.getTabAt(tabPosition)?.select()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, fragment)
        }.commitAllowingStateLoss()
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        tabPosition = savedInstanceState?.getInt(TAB_POSITION_EXTRA, 0) ?: 0
    }

    private fun initView() {
        (toolbar.findViewById<TextView>(R.id.title_toolbar)).setText(R.string.contestsList)

        tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition = tab?.position ?: return

                when (tabPosition) {
                    UNCOMMING_CONTESTS -> presenter.uncommingContestsTabClicked()
                    CURRENT_CONTESTS -> presenter.currentContestsTabClicked()
                    PAST_CONTESTS -> presenter.pastContestsTabClicked()
                }
            }
        })
    }

    companion object {
        private const val TAB_POSITION_EXTRA = "tab_position"
    }
}
