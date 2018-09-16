package com.example.sergey.codeforcesapplication.feature.commandInfo

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.User
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.async

interface CommandInfoPresenter : ProcessingListPresenter<User, CommandInfoView>

class CommandInfoPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingListPresenterImpl<User, CommandInfoView>(), CommandInfoPresenter {

    override fun loadedFunction() = async {
        val handlers = getView()?.handlers
                ?: return@async Response.FAILED<List<User>>()
        return@async contestsRepository.getUsersInfo(handlers).await()
    }

    override val emptyListMessage: Int
        get() = R.string.commandMembersListIsEmpty
}