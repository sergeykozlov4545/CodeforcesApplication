package com.example.sergey.codeforcesapplication.feature.ratingInfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapterImpl
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange

class RatingChangesListAdapter : DataListAdapterImpl<RatingChange>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolderImpl<RatingChange> {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rating_change_list, parent, false)
        return RatingChangesListViewHolder(view)
    }
}