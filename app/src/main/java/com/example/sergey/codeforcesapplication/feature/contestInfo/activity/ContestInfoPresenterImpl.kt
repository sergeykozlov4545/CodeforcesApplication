package com.example.sergey.codeforcesapplication.feature.contestInfo.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter

interface ContestInfoActivityPresenter : MVPPresenter<ContestInfoActivityView> {
    fun problemsListTabClicked()
    fun rankListTabClicked()
}

class ContestInfoPresenterImpl : BasePresenter<ContestInfoActivityView>(), ContestInfoActivityPresenter {

    override fun viewIsReady() {
        getView()?.showProblems()
    }

    override fun problemsListTabClicked() {
        getView()?.showProblems()
    }

    override fun rankListTabClicked() {
        getView()?.showRankList()
    }
}