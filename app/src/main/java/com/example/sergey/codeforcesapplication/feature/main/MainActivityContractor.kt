package com.example.sergey.codeforcesapplication.feature.main

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface MainActivityContractor {
    interface MainActivityView : MVPView {
        fun getPresenter(): MainActivityPresenter<MainActivityView>
        fun showMessage(messageId: Int)
        fun showProgress()
        fun hideProgress()
        fun showContests(contestsList: List<Contest>)
        fun hideContests()
        fun showEmptyListMessage()
        fun hideEmptyListMessage()
        fun showContestInfoActivity(contest: Contest)
    }

    interface MainActivityPresenter<MainActivityView> : MVPPresenter<MainActivityView> {
        fun uncommingContestsTabClicked()
        fun currentContestsTabClicked()
        fun pastContestsTabClicked()
        fun contestCardClicked(contest: Contest)
    }
}