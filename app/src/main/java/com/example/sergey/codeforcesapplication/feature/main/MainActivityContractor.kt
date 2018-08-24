package com.example.sergey.codeforcesapplication.feature.main

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface MainActivityContractor {
    interface MainActivityView : MVPView {
        fun showProgress()
        fun hideProgress()
        fun showContests(contestsList: List<Contest>)
        fun hideContests()
        fun showEmptyListMessage()
        fun hideEmptyListMessage()
    }

    interface MainActivityPresenter<MainActivityView> : MVPPresenter<MainActivityView> {
        fun uncommingContestsTabClicked()
        fun currentContestsTabClicked()
        fun pastContestsTabClicked()
    }
}