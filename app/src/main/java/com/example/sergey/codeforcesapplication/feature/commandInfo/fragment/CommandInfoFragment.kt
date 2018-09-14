package com.example.sergey.codeforcesapplication.feature.commandInfo.fragment

import android.os.Bundle
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.BACKGROUND_COLOR_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.VISIBLE_DIVIDERS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.commandInfo.CommandInfoActivity.Companion.COMMAND_HANDLERS_EXTRA
import com.example.sergey.codeforcesapplication.model.pojo.User

interface CommandInfoFragmentView : ProcessingListView<User> {
    val handlers: List<String>
}

class CommandInfoFragment :
        ProcessingListFragment<User, CommandInfoFragmentView>(), CommandInfoFragmentView {

    override val processingContainer by lazy { CommandInfoContainerFactory.create(context!!) }
    override val presenter by lazy { CommandInfoFragmentPresenterFactory.create(context!!) }

    override val handlers by lazy {
        arguments?.getString(COMMAND_HANDLERS_EXTRA)?.split(",")
                ?: run {
                    activity?.finish()
                    return@run emptyList<String>()
                }
    }

    companion object {
        fun create(handlers: String) = CommandInfoFragment().apply {
            arguments = Bundle().apply {
                putInt(BACKGROUND_COLOR_EXTRA, android.R.color.white)
                putBoolean(VISIBLE_DIVIDERS_EXTRA, true)
                putString(COMMAND_HANDLERS_EXTRA, handlers)
            }
        }
    }
}