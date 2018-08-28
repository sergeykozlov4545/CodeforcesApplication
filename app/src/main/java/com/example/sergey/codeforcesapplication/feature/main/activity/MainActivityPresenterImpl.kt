package com.example.sergey.codeforcesapplication.feature.main.activity

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.TabbedActivityPresenter
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface MainActivityPresenter : TabbedActivityPresenter<MainActivityView> {
    fun uncommingContestsTabClicked()
    fun currentContestsTabClicked()
    fun pastContestsTabClicked()
    fun contestCardClicked(contest: Contest)
}

class MainActivityPresenterImpl : BasePresenter<MainActivityView>(), MainActivityPresenter {

    override fun viewIsReady() {
        getView()?.showUncommingContests()
    }

    override fun restoredView(tabPosition: Int) {
        getView()?.selectTab(tabPosition)
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

    companion object {
        const val UNCOMMING_CONTESTS = 0
        const val CURRENT_CONTESTS = 1
        const val PAST_CONTESTS = 2
    }
}