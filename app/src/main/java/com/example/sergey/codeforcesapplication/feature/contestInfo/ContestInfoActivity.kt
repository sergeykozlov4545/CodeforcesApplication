package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.model.pojo.Problem
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class ContestInfoActivity : AppCompatActivity(), ContestInfoContractor.View {

    private var contestId: Long = 0
    private lateinit var contestName: String

    private lateinit var presenter: ContestInfoContractor.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_info)

        restoreData(savedInstanceState)

        initView()
        initPresenter()
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun showProblems(problems: List<Problem>) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, ProblemsListFragment())
        }.commitAllowingStateLoss()
    }

    override fun showRankList(rankList: List<RankListRow>) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, ContestStanfingsFragment())
        }.commitAllowingStateLoss()
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        contestId = intent.getLongExtra(CONTEST_ID_EXTRA, 0)
        contestName = intent.getStringExtra(CONTEST_NAME_EXTRA)
    }

    private fun initView() {
        initToolbar()

        tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    PROBLEMS -> presenter.problemsListTabClicked()
                    STANDINGS -> presenter.rankListTabClicked()
                    else -> {
                    }
                }
            }
        })
    }

    private fun initToolbar() {
        (toolbar.findViewById<TextView>(R.id.title_toolbar)).text = contestName
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initPresenter() {
        presenter = ContestInfoPresenterImpl()
    }

    companion object {
        const val CONTEST_ID_EXTRA = "contest_id"
        const val CONTEST_NAME_EXTRA = "contest_name"

        private const val PROBLEMS = 0
        private const val STANDINGS = 1
    }
}