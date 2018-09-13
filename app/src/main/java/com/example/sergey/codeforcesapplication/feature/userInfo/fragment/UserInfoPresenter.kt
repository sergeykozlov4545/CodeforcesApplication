package com.example.sergey.codeforcesapplication.feature.userInfo.fragment

import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.User
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.async

interface UserInfoPresenter : ProcessingPresenter<User, UserInfoFragmentView>

class UserInfoPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingPresenterImpl<User, UserInfoFragmentView>(), UserInfoPresenter {

    override fun loadedFunction() = async {
        val handler = getView()?.getUserHandler()
                ?: return@async Response.FAILED<User>()

        val response = contestsRepository.getUsersInfo(arrayListOf(handler)).await()
        return@async if (!response.isSuccess) {
            Response.FAILED(comment = response.comment)
        } else {
            Response(result = response.result!![0])
        }
    }
}