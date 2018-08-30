package com.example.sergey.codeforcesapplication.feature.command.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter

interface CommandInfoActivityPresenter : MVPPresenter<CommandInfoActivityView> {
    fun commandListItemClicked(userHandler: String)
}

class CommandInfoActivityPresenterImpl :
        BasePresenter<CommandInfoActivityView>(), CommandInfoActivityPresenter {

    override fun viewIsReady() {
        getView()?.showUsers()
    }

    override fun commandListItemClicked(userHandler: String) {
        getView()?.showUserInfoActivity(userHandler)
    }
}