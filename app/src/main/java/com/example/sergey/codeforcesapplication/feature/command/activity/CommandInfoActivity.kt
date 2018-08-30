package com.example.sergey.codeforcesapplication.feature.command.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.feature.base.ToolbarActivity
import com.example.sergey.codeforcesapplication.feature.command.fragment.CommandInfoFragment
import com.example.sergey.codeforcesapplication.feature.command.fragment.CommandInfoFragment.Companion.HANDLERS_EXTRA
import com.example.sergey.codeforcesapplication.feature.userInfo.UserInfoActivity
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow
import com.example.sergey.codeforcesapplication.model.pojo.User

interface CommandInfoActivityView : MVPView {
    fun getPresenter(): CommandInfoActivityPresenter
    fun showUsers()
    fun showUserInfoActivity(userHandler: String)
}

class CommandInfoActivity : ToolbarActivity(), CommandInfoActivityView {

    private lateinit var teamName: String
    private lateinit var handlers: String

    private val presenter = CommandInfoActivityPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_command_info)

        restoreData(savedInstanceState)

        setToolbarTitle(teamName)
        showBackAction()
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putString(COMMAND_NAME_EXTRA, teamName)
            putString(HANDLERS_EXTRA, handlers)
        }
    }

    override fun getPresenter() = presenter

    override fun showUsers() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, getCommandInfoFragment())
        }.commitAllowingStateLoss()
    }

    override fun showUserInfoActivity(userHandler: String) {
        UserInfoActivity.start(this, userHandler)
    }

    private fun getCommandInfoFragment(): Fragment {
        val fragment = CommandInfoFragment()

        fragment.arguments = Bundle().apply {
            putString(HANDLERS_EXTRA, handlers)
        }

        return fragment
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        teamName = savedInstanceState?.getString(COMMAND_NAME_EXTRA)
                ?: intent.getStringExtra(COMMAND_NAME_EXTRA)
        handlers = savedInstanceState?.getString(HANDLERS_EXTRA)
                ?: intent.getStringExtra(HANDLERS_EXTRA)
    }

    companion object {
        private const val COMMAND_NAME_EXTRA = "command_name"

        fun start(context: Context, rankListRow: RankListRow) {
            val intent = Intent(context, CommandInfoActivity::class.java).apply {
                putExtra(COMMAND_NAME_EXTRA, rankListRow.party.teamName)

                val handlersString = rankListRow.party.members
                        .joinToString(separator = ",") { member -> member.handle }
                putExtra(HANDLERS_EXTRA, handlersString)
            }

            context.startActivity(intent)
        }
    }
}