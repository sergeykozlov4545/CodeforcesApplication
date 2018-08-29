package com.example.sergey.codeforcesapplication.feature.command.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.pojo.User

interface CommandInfoActivityPresenter : MVPPresenter<CommandInfoActivityView> {
    fun commandListItemClicked(user: User)
}

class CommandInfoActivityPresenterImpl :
        BasePresenter<CommandInfoActivityView>(), CommandInfoActivityPresenter {

    override fun viewIsReady() {
        getView()?.showUsers()
    }

    override fun commandListItemClicked(user: User) {
        getView()?.showUserInfoActivity(user)
    }
}