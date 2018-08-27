package com.example.sergey.codeforcesapplication.feature.main.fragment

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.ViewWithProccesing
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface ContestsListFragmentContractor {
    interface View : ViewWithProccesing<Contest>

    interface Presenter<View> : MVPPresenter<View>
}