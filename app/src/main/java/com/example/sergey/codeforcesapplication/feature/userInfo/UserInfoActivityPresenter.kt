package com.example.sergey.codeforcesapplication.feature.userInfo

import com.example.sergey.codeforcesapplication.feature.base.BasePresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*

interface UserInfoActivityPresenter : MVPPresenter<UserInfoActivityView>

class UserInfoActivityPresenterImpl(private val contestsRepository: ContestsRepository) :
        BasePresenter<UserInfoActivityView>(), UserInfoActivityPresenter {

    override fun viewIsReady() {
        launch(UI) {
            try {
                getView()?.hideAll()
                val userHandler = getView()?.getUserHandler() ?: return@launch

                getView()?.showProgress()
                val user = contestsRepository.getUsersInfo(Collections.singletonList(userHandler)).await()[0]
                getView()?.hideProgress()

                getView()?.showUserInfo(user)
            } catch (e: Exception) {
                getView()?.hideAll()
                getView()?.showError()
            }
        }
    }
}