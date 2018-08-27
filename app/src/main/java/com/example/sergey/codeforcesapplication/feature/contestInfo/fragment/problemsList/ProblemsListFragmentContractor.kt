package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.ViewWithProccesing
import com.example.sergey.codeforcesapplication.model.pojo.Problem

interface ProblemsListFragmentContractor {
    interface View : ViewWithProccesing<Problem> {
        fun getContestId(): Long
    }

    interface Presenter<View> : MVPPresenter<View>
}