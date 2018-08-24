package com.example.sergey.codeforcesapplication.feature.contestInfo

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.pojo.Problem
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

interface ContestInfoContractor {
    interface View : MVPView {
        fun showProblems(problems: List<Problem>)
        fun showRankList(rankList: List<RankListRow>)
    }

    interface Presenter : MVPPresenter<View> {
        fun problemsListTabClicked()
        fun rankListTabClicked()
    }
}