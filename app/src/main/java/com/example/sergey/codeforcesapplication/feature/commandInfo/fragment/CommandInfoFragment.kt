package com.example.sergey.codeforcesapplication.feature.commandInfo.fragment

import android.os.Bundle
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.commandInfo.CommandInfoActivity
import com.example.sergey.codeforcesapplication.model.pojo.User

interface CommandInfoFragmentView : ProcessingListView<User> {
    fun getHandlers(): List<String>
}

class CommandInfoFragment : ProcessingListFragment<User, CommandInfoFragmentView>(), CommandInfoFragmentView {

    private lateinit var handlers: String

    override val processingContainer by lazy { CommandInfoContainerFactory.create(context!!) }
    override val presenter by lazy { CommandInfoFragmentPresenterFactory.create(context!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handlers = arguments?.getString(CommandInfoActivity.COMMAND_HANDLERS_EXTRA) ?: run {
            activity?.finish()
            return@run ""
        }
    }

    override fun getHandlers() = handlers.split(",")

    companion object {
        fun create(handlers: String) = CommandInfoFragment().apply {
            arguments = Bundle().apply {
                putInt(ProcessingListDataContainerImpl.BACKGROUND_COLOR_EXTRA, android.R.color.white)
                putBoolean(ProcessingListDataContainerImpl.VISIBLE_DIVIDERS_EXTRA, true)
                putString(CommandInfoActivity.COMMAND_HANDLERS_EXTRA, handlers)
            }
        }
    }
}