package com.example.sergey.codeforcesapplication.feature.main.presenter

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenterImpl
import com.example.sergey.codeforcesapplication.feature.main.ContestsFragmentView
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

interface ContestsFragmentPresenter : ProcessingListPresenter<Contest, ContestsFragmentView>

abstract class ContestsFragmentPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingListPresenterImpl<Contest, ContestsFragmentView>(), ContestsFragmentPresenter {

    override fun loadedFunction(): Deferred<Response<List<Contest>>> = async {
        val response = contestsRepository.getContests().await()

        if (response.isSuccess) {
            return@async response.copy(result = prepareData(response.result!!))
        }

        return@async response
    }
}

class UpcommingContestsFragmentPresenter(contestsRepository: ContestsRepository) :
        ContestsFragmentPresenterImpl(contestsRepository) {

    override val emptyListMessage: Int
        get() = R.string.emptyUpcommingContestsList

    override fun prepareData(data: List<Contest>) =
            data.filter(Contest::isUpcomming).sortedBy { it.startTimeSeconds }

}

class CurrentContestsFragmentPresenter(contestsRepository: ContestsRepository) :
        ContestsFragmentPresenterImpl(contestsRepository) {

    override val emptyListMessage: Int
        get() = R.string.emptyCurrentContestsList

    override fun prepareData(data: List<Contest>) =
            data.filter(Contest::isCurrent).sortedBy { it.startTimeSeconds }
}

class PastContestsFragmentPresenter(contestsRepository: ContestsRepository) :
        ContestsFragmentPresenterImpl(contestsRepository) {

    override val emptyListMessage: Int
        get() = R.string.emptyPastContestsList

    override fun prepareData(data: List<Contest>) =
            data.filter(Contest::isPast).sortedByDescending { it.startTimeSeconds }
}