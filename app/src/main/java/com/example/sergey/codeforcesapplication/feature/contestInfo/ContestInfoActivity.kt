package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.activity.TabbedActivity
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.TabInfo
import com.example.sergey.codeforcesapplication.model.pojo.Contest

class ContestInfoActivity : TabbedActivity() {

    override val tabsInfo: List<TabInfo>
        get() = ContestInfoActivityTabsFactory.create(applicationContext, contestId)

    private var contestId: Long = 0
    private lateinit var contestName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_info)

        restoreData(savedInstanceState)

        showBackAction()
        setToolbarTitle(contestName)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putLong(CONTEST_ID_EXTRA, contestId)
            putString(CONTEST_NAME_EXTRA, contestName)
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        contestId = savedInstanceState?.getLong(CONTEST_ID_EXTRA, 0)
                ?: intent.getLongExtra(CONTEST_ID_EXTRA, 0)
        contestName = savedInstanceState?.getString(CONTEST_NAME_EXTRA)
                ?: intent.getStringExtra(CONTEST_NAME_EXTRA)
    }

    companion object {
        private const val CONTEST_ID_EXTRA = "contest_id"
        private const val CONTEST_NAME_EXTRA = "contest_name"

        fun start(context: Context, contest: Contest) {
            context.startActivity(Intent(context, ContestInfoActivity::class.java).apply {
                putExtra(CONTEST_ID_EXTRA, contest.id)
                putExtra(CONTEST_NAME_EXTRA, contest.name)
            })
        }
    }
}