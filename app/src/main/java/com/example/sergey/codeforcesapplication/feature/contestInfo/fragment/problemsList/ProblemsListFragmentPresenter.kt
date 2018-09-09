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
            try {
                getView()?.hideAll()
                val contestId = getView()?.getContestId() ?: return@launch

                getView()?.showProgress()

                val response = contestsRepository.getContestStandings(contestId).await()

                getView()?.hideProgress()

                if (!response.isSuccess) {
                    getView()?.showEmptyListMessage()
                    // TODO: Показать текст response.comment
                    return@launch
                }

                if (response.result.problems.isEmpty()) {
                    getView()?.showEmptyListMessage()
                    return@launch
                }

                getView()?.showDataList(response.result.problems)
            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}