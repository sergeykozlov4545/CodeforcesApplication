package com.example.sergey.codeforcesapplication.feature.userInfo

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*

interface UserInfoActivityPresenter : MVPPresenter<UserInfoActivityView> {
    fun ratingDetailsViewClicked()
}

class UserInfoActivityPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<UserInfoActivityView>(), UserInfoActivityPresenter {

    override fun viewIsReady() {
        launch(UI) {
            try {
                getView()?.hideAll()
                val userHandler = getView()?.getUserHandler() ?: return@launch

                getView()?.showProgress()
                val response = contestsRepository.getUsersInfo(Collections.singletonList(userHandler)).await()
                getView()?.hideProgress()

                if (!response.isSuccess) {
                    // TODO: Показать текст response.comment
                    return@launch
                }

                getView()?.showUserInfo(response.result[0])
            } catch (e: Exception) {
                getView()?.hideAll()
                getView()?.showError()
            }
        }
    }

    override fun ratingDetailsViewClicked() {
        getView()?.showRatingDetailsActivity()
    }
}