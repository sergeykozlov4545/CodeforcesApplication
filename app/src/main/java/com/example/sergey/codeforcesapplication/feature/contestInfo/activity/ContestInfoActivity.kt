package com.example.sergey.codeforcesapplication.feature.contestInfo.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.feature.base.ToolbarActivity
import com.example.sergey.codeforcesapplication.feature.command.CommandActivity
import com.example.sergey.codeforcesapplication.feature.contestInfo.activity.ContestInfoPresenterImpl.Companion.PROBLEMS
import com.example.sergey.codeforcesapplication.feature.contestInfo.activity.ContestInfoPresenterImpl.Companion.STANDINGS
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList.ProblemsListFragment
import com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList.ContestStandingsFragment
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow
import kotlinx.android.synthetic.main.activity_main.*

interface ContestInfoActivityView : MVPView {
    fun getPresenter(): ContestInfoActivityPresenter
    fun showProblems()
    fun showRankList()
    fun selectTab(tabPosition: Int)
    fun showUserInfo(userHandler: String)
    fun showCommandInfo(rankListRow: RankListRow)
}

class ContestInfoActivity : ToolbarActivity(), ContestInfoActivityView {

    private var contestId: Long = 0
    private lateinit var contestName: String

    private val presenter = ContestInfoPresenterImpl()

    private var tabPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_info)

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

    override fun getPresenter() = presenter

    override fun showProblems() = showFragment(ProblemsListFragment())

    override fun showRankList() = showFragment(ContestStandingsFragment())

    override fun selectTab(tabPosition: Int) {
        tabsLayout.getTabAt(tabPosition)?.select()
    }

    override fun showUserInfo(userHandler: String) {
        // TODO() Отобразить инфу об пользователе
    }

    override fun showCommandInfo(rankListRow: RankListRow) {
        CommandActivity.start(this, rankListRow)
    }

    private fun showFragment(fragment: Fragment) {
        fragment.arguments = Bundle().apply {
            putLong(CONTEST_ID_EXTRA, contestId)
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, fragment)
        }.commitAllowingStateLoss()
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        contestId = intent.getLongExtra(CONTEST_ID_EXTRA, 0)
        contestName = intent.getStringExtra(CONTEST_NAME_EXTRA)

        tabPosition = savedInstanceState?.getInt(TAB_POSITION_EXTRA, 0) ?: 0
    }

    private fun initView() {
        initToolbar()

        tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition = tab?.position ?: return

                when (tabPosition) {
                    PROBLEMS -> presenter.problemsListTabClicked()
                    STANDINGS -> presenter.rankListTabClicked()
                }
            }
        })
    }

    private fun initToolbar() {
        showBackAction()
        setToolbarTitle(contestName)
    }

    companion object {
        const val CONTEST_ID_EXTRA = "contest_id"
        const val CONTEST_NAME_EXTRA = "contest_name"

        private const val TAB_POSITION_EXTRA = "tab_position"
    }
}