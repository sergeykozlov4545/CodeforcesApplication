package com.example.sergey.codeforcesapplication.feature.command

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ToolbarActivity
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface CommandActivityView

class CommandActivity : ToolbarActivity(), CommandActivityView {

    private lateinit var teamName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_command)

        restoreData(savedInstanceState)

        setToolbarTitle(teamName)
        showBackAction()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putString(COMMAND_NAME_EXTRA, teamName)
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        teamName = savedInstanceState?.getString(COMMAND_NAME_EXTRA)
                ?: intent.getStringExtra(COMMAND_NAME_EXTRA)
    }

    companion object {
        private const val COMMAND_NAME_EXTRA = "command_name"
        private const val HANDLERS_EXTRA = "handlers"

        fun start(context: Context, rankListRow: RankListRow) {
            val intent = Intent(context, CommandActivity::class.java).apply {
                putExtra(COMMAND_NAME_EXTRA, rankListRow.party.teamName)

                val handlersString = rankListRow.party.members
                        .joinToString(separator = ",") { member -> member.handle }
                putExtra(HANDLERS_EXTRA, handlersString)
            }

            context.startActivity(intent)
        }
    }
}