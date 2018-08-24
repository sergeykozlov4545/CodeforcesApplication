package com.example.sergey.codeforcesapplication.feature.main

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.model.Contest
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivityPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<MainActivityContractor.MainActivityView>(),
        MainActivityContractor.MainActivityPresenter<MainActivityContractor.MainActivityView> {

    override fun viewIsReady() = getContests(contestsRepository::getUncommingContests)

    override fun uncommingContestsTabClicked() = getContests(contestsRepository::getUncommingContests)

    override fun currentContestsTabClicked() = getContests(contestsRepository::getCurrentContests)

    override fun pastContestsTabClicked() = getContests(contestsRepository::getPastContests)

    private fun getContests(loadContestsFunction: () -> Deferred<List<Contest>>) {
        launch(UI) {
            getView()?.hideEmptyListMessage()
            getView()?.hideContests()
            getView()?.showProgress()

            var contests = loadContestsFunction().await()
            getView()?.hideProgress()

            if (contests.isEmpty()) {
                getView()?.showEmptyListMessage()
                return@launch
            }

            getView()?.showContests(contests)
        }
    }
}