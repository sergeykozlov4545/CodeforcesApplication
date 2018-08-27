package com.example.sergey.codeforcesapplication.feature.main.fragment

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface ContestsListFragmentContractor {
    interface View : MVPView {
        fun showProgress()
        fun hideProgress()
        fun showEmptyListMessage()
        fun showContests(contestsList: List<Contest>)
    }

    interface Presenter<View> : MVPPresenter<View>
}