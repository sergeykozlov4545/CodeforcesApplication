package com.example.sergey.codeforcesapplication.feature.contestInfo.activity

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView

interface ContestInfoContractor {
    interface View : MVPView {
        fun showProblems()
        fun showRankList()
    }

    interface Presenter : MVPPresenter<View> {
        fun problemsListTabClicked()
        fun rankListTabClicked()
    }
}