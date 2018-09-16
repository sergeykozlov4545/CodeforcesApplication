package com.example.sergey.codeforcesapplication.feature.userInfo

import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.User
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.async

interface UserInfoPresenter : ProcessingPresenter<User, UserInfoView>

class UserInfoPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingPresenterImpl<User, UserInfoView>(), UserInfoPresenter {

    override fun loadedFunction() = async {
        val handler = getView()?.userHandler
                ?: return@async Response.FAILED<User>()

        val response = contestsRepository.getUsersInfo(arrayListOf(handler)).await()
        return@async if (!response.isSuccess) {
            Response.FAILED(comment = response.comment)
        } else {
            Response(result = response.result!![0])
        }
    }
}