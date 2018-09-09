package com.example.sergey.codeforcesapplication.feature.ratingDetails

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface RatingChangesListActivityPresenter : MVPPresenter<RatingDetailsActivityView>

class RatingChangesListActivityPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<RatingDetailsActivityView>(), RatingChangesListActivityPresenter {

    override fun viewIsReady() {
        launch(UI) {
            try {
                getView()?.hideAll()

                val userHandler = getView()?.getUserHandler() ?: return@launch

                getView()?.showProgress()
                val response = contestsRepository.getUserRatingChangesList(userHandler).await()

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

                getView()?.showDataList(response.result.sortedByDescending { it.contestId })
            } catch (e: Exception) {
                getView()?.hideAll()
                getView()?.showError()
            }
        }
    }
}
