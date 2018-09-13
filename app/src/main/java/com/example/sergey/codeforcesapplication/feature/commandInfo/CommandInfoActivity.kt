package com.example.sergey.codeforcesapplication.feature.commandInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.activity.ProcessingFragmentActivity
import com.example.sergey.codeforcesapplication.feature.commandInfo.fragment.CommandInfoFragment
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

class CommandInfoActivity : ProcessingFragmentActivity() {

    override val fragment: Fragment
        get() = CommandInfoFragment.create(handlers)

    private lateinit var teamName: String
    private lateinit var handlers: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_info)
        restoreData(savedInstanceState)
        showBackAction()
        setToolbarTitle(teamName)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putString(COMMAND_NAME_EXTRA, teamName)
            putString(COMMAND_HANDLERS_EXTRA, handlers)
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        teamName = savedInstanceState?.getString(COMMAND_NAME_EXTRA)
                ?: intent.getStringExtra(COMMAND_NAME_EXTRA)
        handlers = savedInstanceState?.getString(COMMAND_HANDLERS_EXTRA)
                ?: intent.getStringExtra(COMMAND_HANDLERS_EXTRA)
    }

    companion object {
        private const val COMMAND_NAME_EXTRA = "command_name"
        const val COMMAND_HANDLERS_EXTRA = "handlers"

        fun start(context: Context, rankListRow: RankListRow) {
            // TODO: Использовать KTX
            context.startActivity(
                    Intent(context, CommandInfoActivity::class.java).apply {
                        putExtra(COMMAND_NAME_EXTRA, rankListRow.party.teamName)
                        putExtra(COMMAND_HANDLERS_EXTRA, rankListRow.party.membersString)
                    }
            )
        }
    }
}