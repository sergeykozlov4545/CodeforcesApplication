package com.example.sergey.codeforcesapplication.feature.command.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.feature.base.ViewWithProcessingOld
import com.example.sergey.codeforcesapplication.feature.base.WithProcessingFragment
import com.example.sergey.codeforcesapplication.model.pojo.User

interface CommandInfoFragmentView : ViewWithProcessingOld<User> {
    fun getHandlers(): List<String>
}

class CommandInfoFragment : WithProcessingFragment<User>(), CommandInfoFragmentView {

    private lateinit var handlers: String

    private val commandInfoListAdapter = CommandInfoListAdapter()

    private lateinit var presenter: CommandInfoFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handlers = arguments?.getString(HANDLERS_EXTRA) ?: return // TODO: Закрыть экран

        initPresenter()
    }

    override fun onResume() {
        super.onResume()

        setDataListBackground(ContextCompat.getColor(context!!, android.R.color.white))
        showDataListDividers()

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun onPause() {
        super.onPause()

        presenter.detachView()
    }

    override fun getEmptyListMessageText() = getString(R.string.commandMembersListIsEmpty)

    override fun getDataListLayoutManager() = LinearLayoutManager(context!!.applicationContext)

    override fun getDataListAdapter() = commandInfoListAdapter

    override fun getHandlers() = handlers.split(",")

    private fun initPresenter() {
        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = CommandInfoFragmentPresenterImpl(repository)
    }

    companion object {
        const val HANDLERS_EXTRA = "handlers"

    }
}