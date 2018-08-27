package com.example.sergey.codeforcesapplication.feature.main.activity

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView

interface MainActivityContractor {
    interface MainActivityView : MVPView {
        fun showUncommingContests()
        fun showCurrentContests()
        fun showPastContests()
    }

    interface MainActivityPresenter<MainActivityView> : MVPPresenter<MainActivityView> {
        fun uncommingContestsTabClicked()
        fun currentContestsTabClicked()
        fun pastContestsTabClicked()
    }
}