package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.Problem
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.async

interface ProblemsListFragmentPresenter : ProcessingListPresenter<Problem, ProblemsListFragmentView>

class ProblemsListFragmentPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingListPresenterImpl<Problem, ProblemsListFragmentView>(),
        ProblemsListFragmentPresenter {

    override fun loadedFunction() = async {
        val contestId = getView()?.getContestId()
                ?: return@async Response.FAILED<List<Problem>>()

        val response = contestsRepository.getContestStandings(contestId).await()

        return@async if (!response.isSuccess) {
            Response.FAILED(comment = response.comment)
        } else {
            Response(result = response.result!!.problems)
        }
    }

    override val emptyListMessage: Int
        get() = R.string.problemsListIsEmpty

}