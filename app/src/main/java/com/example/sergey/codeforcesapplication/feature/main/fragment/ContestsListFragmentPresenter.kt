package com.example.sergey.codeforcesapplication.feature.main.fragment

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

abstract class ContestsListFragmentPresenter : BasePresenter<ContestsListFragmentContractor.View>(),
        ContestsListFragmentContractor.Presenter<ContestsListFragmentContractor.View> {

    override fun contestCardClicked(contest: Contest) {
        if (contest.isUpcomming) {
            getView()?.showMessage(R.string.contest_is_not_started)
            return
        }

        getView()?.showContestInfoActivity(contest)
    }

    protected fun getContests(loadContestsFunction: () -> Deferred<List<Contest>>) {
        launch(UI) {
            getView()?.showProgress()

            val contests = loadContestsFunction().await()
            getView()?.hideProgress()

            if (contests.isEmpty()) {
                getView()?.showEmptyListMessage()
                return@launch
            }

            getView()?.showContests(contests)
        }
    }
}

class UncommingContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenter() {

    override fun viewIsReady() {
        getContests(contestsRepository::getUncommingContests)
    }
}

class CurrentContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenter() {

    override fun viewIsReady() {
        getContests(contestsRepository::getCurrentContests)
    }
}

class PastContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenter() {

    override fun viewIsReady() {
        getContests(contestsRepository::getPastContests)
    }
}