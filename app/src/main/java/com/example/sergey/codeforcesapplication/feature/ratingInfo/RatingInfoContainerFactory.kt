package com.example.sergey.codeforcesapplication.feature.ratingInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.ratingInfo.adapter.RatingChangesListAdapter
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange

object RatingInfoContainerFactory {
    fun create(context: Context) = ProcessingListDataContainerImpl<RatingChange>().apply {
        setAdapter(RatingChangesListAdapter())
    }
}