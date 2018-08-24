package com.example.sergey.codeforcesapplication.feature.contestInfo

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter

class ContestInfoPresenterImpl :
        BasePresenter<ContestInfoContractor.View>(), ContestInfoContractor.Presenter {

    override fun viewIsReady() {
        TODO("not implemented")
    }

    override fun problemsListTabClicked() {
        getView()?.showProblems(emptyList())
    }

    override fun rankListTabClicked() {
        getView()?.showRankList(emptyList())
    }
}