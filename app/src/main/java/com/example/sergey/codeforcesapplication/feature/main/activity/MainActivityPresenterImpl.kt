package com.example.sergey.codeforcesapplication.feature.main.activity

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.model.pojo.Contest

class MainActivityPresenterImpl :
        BasePresenter<MainActivityContractor.View>(),
        MainActivityContractor.Presenter<MainActivityContractor.View> {

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

    override fun contestCardClicked(contest: Contest) {
        if (contest.isUpcomming) {
            getView()?.showMessage(R.string.contest_is_not_started)
            return
        }

        getView()?.showContestInfoActivity(contest)
    }
}