package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

interface ContestStandingsPresenter : ProcessingListPresenter<RankListRow, ContestStandingsFragmentView>

class ContestStandingsPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingListPresenterImpl<RankListRow, ContestStandingsFragmentView>(),
        ContestStandingsPresenter {

    override fun loadedFunction(): Deferred<Response<List<RankListRow>>> = async {
        val contestId = getView()?.getContestId()
                ?: return@async Response<List<RankListRow>>(status = "FAILED")

        val contestStandingsResponse = contestsRepository.getContestStandings(contestId).await()

        if (!contestStandingsResponse.isSuccess) {
            return@async Response<List<RankListRow>>(
                    contestStandingsResponse.status,
                    contestStandingsResponse.comment
            )
        }

        return@async Response(result = contestStandingsResponse.result!!.ranks)
    }

    override val emptyListMessage: Int
        get() = R.string.problemsListIsEmpty

}