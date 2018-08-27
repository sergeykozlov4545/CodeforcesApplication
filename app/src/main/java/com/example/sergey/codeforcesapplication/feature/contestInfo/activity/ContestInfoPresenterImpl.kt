package com.example.sergey.codeforcesapplication.feature.contestInfo.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter

class ContestInfoPresenterImpl :
        BasePresenter<ContestInfoContractor.View>(), ContestInfoContractor.Presenter {

    override fun viewIsReady() {
        getView()?.showProblems()
    }

    override fun problemsListTabClicked() {
        getView()?.showProblems()
    }

    override fun rankListTabClicked() {
        getView()?.showRankList()
    }
}