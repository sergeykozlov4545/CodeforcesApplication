package com.example.sergey.codeforcesapplication.feature.main.fragment

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface ContestsListFragmentPresenter : MVPPresenter<ContestsListFragmentView>

abstract class ContestsListFragmentPresenterImpl :
        BasePresenter<ContestsListFragmentView>(), ContestsListFragmentPresenter {

    override fun viewIsReady() {
        getContests()
    }

    abstract suspend fun loadFunction(): Response<List<Contest>>

    private fun getContests() {
        launch(UI) {
            try {
                getView()?.hideAll()

                getView()?.showProgress()

                val response = loadFunction()
                getView()?.hideProgress()

                if (!response.isSuccess) {
                    getView()?.showEmptyListMessage()
                    // TODO: Показать текст response.comment
                    return@launch
                }

                if (response.result.isEmpty()) {
                    getView()?.showEmptyListMessage()
                    return@launch
                }

                getView()?.showDataList(response.result)
            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}

class UncommingContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenterImpl() {

    override suspend fun loadFunction(): Response<List<Contest>> {
        val response = contestsRepository.getContests().await()
        return response.apply {
            if (isSuccess) {
                result.filter(Contest::isUpcomming).sortedBy { it.startTimeSeconds }
            }
        }
    }
}

class CurrentContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenterImpl() {

    override suspend fun loadFunction(): Response<List<Contest>> {
        val response = contestsRepository.getContests().await()
        return response.apply {
            if (isSuccess) {
                result.filter(Contest::isCurrent).sortedBy { it.startTimeSeconds }
            }
        }
    }
}

class PastContestsListFragmentPresenter(private val contestsRepository: ContestsRepository) :
        ContestsListFragmentPresenterImpl() {

    override suspend fun loadFunction(): Response<List<Contest>> {
        val response = contestsRepository.getContests().await()
        return response.apply {
            if (isSuccess) {
                result.filter(Contest::isPast).sortedByDescending { it.startTimeSeconds }
            }
        }
    }
}