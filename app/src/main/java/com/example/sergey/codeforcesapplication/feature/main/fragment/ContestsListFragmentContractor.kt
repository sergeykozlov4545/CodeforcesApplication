package com.example.sergey.codeforcesapplication.feature.main.fragment

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface ContestsListFragmentContractor {
    interface View : MVPView {
        fun getPresenter(): Presenter<View>
        fun showMessage(messageId: Int)
        fun showProgress()
        fun hideProgress()
        fun showEmptyListMessage()
        fun showContests(contestsList: List<Contest>)
        fun showContestInfoActivity(contest: Contest)
    }

    interface Presenter<View> : MVPPresenter<View> {
        fun contestCardClicked(contest: Contest)
    }
}