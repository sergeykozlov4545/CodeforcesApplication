package com.example.sergey.codeforcesapplication.feature.commandInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.BACKGROUND_COLOR_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.VISIBLE_DIVIDERS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.activity.ProcessingActivity
import com.example.sergey.codeforcesapplication.feature.base.activity.ProcessingListActivity
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow
import com.example.sergey.codeforcesapplication.model.pojo.User

interface CommandInfoView : ProcessingListView<User> {
    val handlers: List<String>
}

class CommandInfoActivity :
        ProcessingListActivity<User, CommandInfoView>(), CommandInfoView {

    override val processingContainer by lazy { CommandInfoContainerFactory.create(this) }
    override val presenter by lazy { CommandInfoPresenterFactory.create(this) }
    override val handlers by lazy { handlersString.split(",") }

    private lateinit var teamName: String
    private lateinit var handlersString: String

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
            putString(COMMAND_HANDLERS_EXTRA, handlersString)
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        teamName = savedInstanceState?.getString(COMMAND_NAME_EXTRA)
                ?: intent.getStringExtra(COMMAND_NAME_EXTRA)
        handlersString = savedInstanceState?.getString(COMMAND_HANDLERS_EXTRA)
                ?: intent.getStringExtra(COMMAND_HANDLERS_EXTRA)
    }

    companion object {
        private const val COMMAND_NAME_EXTRA = "command_name"
        const val COMMAND_HANDLERS_EXTRA = "handlers"

        fun start(context: Context, rankListRow: RankListRow) {
            // TODO: Использовать KTX
            context.startActivity(
                    Intent(context, CommandInfoActivity::class.java).apply {
                        putExtra(ProcessingActivity.PARENT_ID_EXTRA, R.id.processingContainer)
                        putExtra(ProcessingActivity.ARGUMENTS_BUNDLE_EXTRA, Bundle().apply {
                            putInt(BACKGROUND_COLOR_EXTRA, android.R.color.white)
                            putBoolean(VISIBLE_DIVIDERS_EXTRA, true)
                        })
                        putExtra(COMMAND_NAME_EXTRA, rankListRow.party.teamName)
                        putExtra(COMMAND_HANDLERS_EXTRA, rankListRow.party.membersString)
                    }
            )
        }
    }
}