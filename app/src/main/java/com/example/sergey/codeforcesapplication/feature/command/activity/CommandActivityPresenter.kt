package com.example.sergey.codeforcesapplication.feature.command.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter

interface CommandActivityPresenter : MVPPresenter<CommandActivityView>

class CommandActivityPresenterImpl :
        BasePresenter<CommandActivityView>(), CommandActivityPresenter {

    override fun viewIsReady() {
        getView()?.showUsers()
    }
}