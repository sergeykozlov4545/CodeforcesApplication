package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


interface ProblemsListFragmentPresenter : MVPPresenter<ProblemsListFragmentView>

class ProblemsListFragmentPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<ProblemsListFragmentView>(), ProblemsListFragmentPresenter {

    override fun viewIsReady() {
        launch(UI) {
            val contestId = getView()?.getContestId() ?: return@launch

            getView()?.showProgress()

            val contestInfo = contestsRepository.getContestStandings(contestId).await()

            getView()?.hideProgress()

            if (contestInfo.problems.isEmpty()) {
                getView()?.showEmptyListMessage()
                return@launch
            }

            getView()?.showDataList(contestInfo.problems)
        }
    }
}