package com.example.sergey.codeforcesapplication.feature.contestInfo.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.TabbedActivityPresenter
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface ContestInfoActivityPresenter : TabbedActivityPresenter<ContestInfoActivityView> {
    fun problemsListTabClicked()
    fun rankListTabClicked()
    fun rankListItemClicked(rankListRow: RankListRow)
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

    override fun rankListItemClicked(rankListRow: RankListRow) {
        if (rankListRow.party.teamName.isNullOrEmpty()) {
            getView()?.showUserInfo(rankListRow.party.members[0].handle)
        } else {
            getView()?.showCommandInfo(rankListRow)
        }
    }

    companion object {
        const val PROBLEMS = 0
        const val STANDINGS = 1
    }
}