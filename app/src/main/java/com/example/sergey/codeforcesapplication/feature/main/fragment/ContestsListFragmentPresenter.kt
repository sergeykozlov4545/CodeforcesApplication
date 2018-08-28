package com.example.sergey.codeforcesapplication.feature.main.fragment

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface ContestsListFragmentPresenter : MVPPresenter<ContestsListFragmentView>

abstract class ContestsListFragmentPresenterImpl :
        BasePresenter<ContestsListFragmentView>(), ContestsListFragmentPresenter {

    protected fun getContests(loadContestsFunction: () -> Deferred<List<Contest>>) {
        launch(UI) {
            try {
                getView()?.hideAll()

                getView()?.showProgress()

                val contests = loadContestsFunction().await()
                getView()?.hideProgress()

                if (contests.isEmpty()) {
                    getView()?.showEmptyListMessage()
                    return@launch
                }

                getView()?.showDataList(contests)
            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}

class UncommingContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenterImpl() {

    override fun viewIsReady() {
        getContests(contestsRepository::getUncommingContests)
    }
}

class CurrentContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenterImpl() {

    override fun viewIsReady() {
        getContests(contestsRepository::getCurrentContests)
    }
}

class PastContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenterImpl() {

    override fun viewIsReady() {
        getContests(contestsRepository::getPastContests)
    }
}