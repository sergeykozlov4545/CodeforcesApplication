package com.example.sergey.codeforcesapplication.feature.command.fragment

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface CommandInfoFragmentPresenter : MVPPresenter<CommandInfoFragmentView>

class CommandInfoFragmentPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<CommandInfoFragmentView>(), CommandInfoFragmentPresenter {

    override fun viewIsReady() {
        launch(UI) {
            try {
                val handlers = getView()?.getHandlers() ?: return@launch

                getView()?.hideAll()
                getView()?.showProgress()

                val response = contestsRepository.getUsersInfo(handlers).await()

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