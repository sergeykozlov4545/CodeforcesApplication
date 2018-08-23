package com.example.sergey.codeforcesapplication.feature.main

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.model.Contest

class MainActivityPresenterImpl :
        BasePresenter<MainContractor.MainActivityView>(),
        MainContractor.MainActivityPresenter<MainContractor.MainActivityView> {

    override fun viewIsReady() {
        getView()?.showContests(getMockContestsList("Предстоящие"))
    }

    override fun uncommingContestsTabClicked() {
        getView()?.showContests(getMockContestsList("Предстоящие")) // TODO: Получать только нужные
    }

    override fun currentContestsTabClicked() {
        getView()?.showContests(getMockContestsList("Текущие")) // TODO: Получать только нужные
    }

    override fun pastContestsTabClicked() {
        getView()?.showContests(getMockContestsList("Прошедшие")) // TODO: Получать только нужные
    }

    private fun getMockContestsList(prefix: String): List<Contest> {
        val contests: MutableList<Contest> = ArrayList()

        for (i in 0 until 100) {
            contests.add(Contest(i, "$prefix: Technocup 2018 - Elimination Round #$i", 1537693500, 9000))
        }

        return contests
    }
}