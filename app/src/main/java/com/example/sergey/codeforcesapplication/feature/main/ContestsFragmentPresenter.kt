package com.example.sergey.codeforcesapplication.feature.main

import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

interface ContestsFragmentPresenter : ProcessingPresenter<List<Contest>, ContestsFragmentView>

open class ContestsFragmentPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingPresenterImpl<List<Contest>, ContestsFragmentView>(), ContestsFragmentPresenter {

    override fun loadedFunction(): Deferred<Response<List<Contest>>> = async {
        val response = contestsRepository.getContests().await()

        if (response.isSuccess) {
            return@async response.copy(result = prepareData(response.result!!))
        }

        return@async response
    }
}

class UncommingContestsFragmentPresenter(contestsRepository: ContestsRepository) :
        ContestsFragmentPresenterImpl(contestsRepository) {

    override fun prepareData(data: List<Contest>) =
            data.filter(Contest::isUpcomming).sortedBy { it.startTimeSeconds }
}

class CurrentContestsFragmentPresenter(contestsRepository: ContestsRepository) :
        ContestsFragmentPresenterImpl(contestsRepository) {

    override fun prepareData(data: List<Contest>) =
            data.filter(Contest::isCurrent).sortedBy { it.startTimeSeconds }
}

class PastContestsFragmentPresenter(contestsRepository: ContestsRepository) :
        ContestsFragmentPresenterImpl(contestsRepository) {

    override fun prepareData(data: List<Contest>) =
            data.filter(Contest::isPast).sortedByDescending { it.startTimeSeconds }
}