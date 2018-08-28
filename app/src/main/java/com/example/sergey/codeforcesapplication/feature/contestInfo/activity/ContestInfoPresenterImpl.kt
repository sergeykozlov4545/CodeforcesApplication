package com.example.sergey.codeforcesapplication.feature.contestInfo.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.TabbedActivityPresenter

interface ContestInfoActivityPresenter : TabbedActivityPresenter<ContestInfoActivityView> {
    fun problemsListTabClicked()
    fun rankListTabClicked()
}

class ContestInfoPresenterImpl : BasePresenter<ContestInfoActivityView>(), ContestInfoActivityPresenter {

    override fun viewIsReady() {
        getView()?.showProblems()
    }

    override fun restoredView(tabPosition: Int) {
        getView()?.selectTab(tabPosition)
    }

    override fun problemsListTabClicked() {
        getView()?.showProblems()
    }

    override fun rankListTabClicked() {
        getView()?.showRankList()
    }

    companion object {
        const val PROBLEMS = 0
        const val STANDINGS = 1
    }
}