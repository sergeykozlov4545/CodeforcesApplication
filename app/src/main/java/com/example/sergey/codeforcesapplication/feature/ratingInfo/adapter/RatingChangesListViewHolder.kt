package com.example.sergey.codeforcesapplication.feature.ratingInfo.adapter

import android.annotation.SuppressLint
import android.view.View
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange
import kotlinx.android.synthetic.main.item_rating_change_list.*

class RatingChangesListViewHolder(override val containerView: View?) :
        DataListViewHolderImpl<RatingChange>(containerView) {

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(ratingChange: RatingChange) {
        contestNameView.text = ratingChange.contestName
        rankView.text = ratingChange.rank.toString()
        ratingChangeView.text = "${ratingChange.oldRating} -> ${ratingChange.newRating}"
    }
}