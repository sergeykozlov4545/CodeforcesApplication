package com.example.sergey.codeforcesapplication.feature.main

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.Contest

interface MainContractor {
    interface MainActivityView : MVPView {
        fun showContests(contestsList: List<Contest>)
        fun showEmptyListMessage()
    }

    interface MainActivityPresenter<MainActivityView> : MVPPresenter<MainActivityView> {
        fun uncommingContestsTabClicked()
        fun currentContestsTabClicked()
        fun pastContestsTabClicked()
    }
}