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
                val contestInfo = contestsRepository.getContestStandings(contestId).await()

                getView()?.hideProgress()

                if (contestInfo.ranks.isEmpty()) {
                    getView()?.showEmptyListMessage()
                    return@launch
                }

                getView()?.showDataList(contestInfo.ranks)

            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}