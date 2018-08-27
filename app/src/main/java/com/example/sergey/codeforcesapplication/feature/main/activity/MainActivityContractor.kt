package com.example.sergey.codeforcesapplication.feature.main.activity

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface MainActivityContractor {
    interface View : MVPView {
        fun getPresenter(): Presenter<View>
        fun showMessage(messageId: Int)
        fun showUncommingContests()
        fun showCurrentContests()
        fun showPastContests()
        fun showContestInfoActivity(contest: Contest)
    }

    interface Presenter<View> : MVPPresenter<View> {
        fun uncommingContestsTabClicked()
        fun currentContestsTabClicked()
        fun pastContestsTabClicked()
        fun contestCardClicked(contest: Contest)
    }
}