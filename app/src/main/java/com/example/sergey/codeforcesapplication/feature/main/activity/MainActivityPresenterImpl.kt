package com.example.sergey.codeforcesapplication.feature.main.activity

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter

class MainActivityPresenterImpl :
        BasePresenter<MainActivityContractor.MainActivityView>(),
        MainActivityContractor.MainActivityPresenter<MainActivityContractor.MainActivityView> {

    override fun viewIsReady() {
        getView()?.showUncommingContests()
    }

    override fun uncommingContestsTabClicked() {
        getView()?.showUncommingContests()
    }

    override fun currentContestsTabClicked() {
        getView()?.showCurrentContests()
    }

    override fun pastContestsTabClicked() {
        getView()?.showPastContests()
    }
}