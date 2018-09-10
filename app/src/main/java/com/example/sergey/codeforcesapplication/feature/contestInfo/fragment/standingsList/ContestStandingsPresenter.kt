package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface ContestStandingsPresenter : MVPPresenter<ContestStandingsView>

class ContestStandingsPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<ContestStandingsView>(), ContestStandingsPresenter {

    override fun viewIsReady() {
        launch(UI) {
            try {
                getView()?.hideAll()
                val contestId = getView()?.getContestId() ?: return@launch

                getView()?.showProgress()

                val response = contestsRepository.getContestStandings(contestId).await()

                getView()?.hideProgress()

                if (!response.isSuccess) {
                    getView()?.showEmptyListMessage()
                    getView()?.showErrorOperation(response.comment!!)
                    return@launch
                }

                if (response.result.ranks.isEmpty()) {
                    getView()?.showEmptyListMessage()
                    return@launch
                }

                getView()?.showDataList(response.result.ranks)

            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}