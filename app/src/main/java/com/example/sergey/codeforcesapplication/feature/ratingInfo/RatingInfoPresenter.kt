package com.example.sergey.codeforcesapplication.feature.ratingInfo

import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenter
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingListPresenterImpl
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange
import com.example.sergey.codeforcesapplication.model.remote.Response
import com.example.sergey.codeforcesapplication.model.repository.ContestsRepository
import kotlinx.coroutines.experimental.async

interface RatingInfoPresenter : ProcessingListPresenter<RatingChange, RatingInfoView>

class RatingInfoPresenterImpl(private val contestsRepository: ContestsRepository) :
        ProcessingListPresenterImpl<RatingChange, RatingInfoView>(), RatingInfoPresenter {

    override fun loadedFunction() = async {
        val handler = getView()?.userHandler
                ?: return@async Response.FAILED<List<RatingChange>>()

        return@async contestsRepository.getUserRatingChangesList(handler).await()
    }

    override val emptyListMessage: Int
        get() = R.string.ratingChangeListIsEmpty

    override fun prepareData(data: List<RatingChange>): List<RatingChange> {
        return data.sortedByDescending { it.contestId }
    }
}