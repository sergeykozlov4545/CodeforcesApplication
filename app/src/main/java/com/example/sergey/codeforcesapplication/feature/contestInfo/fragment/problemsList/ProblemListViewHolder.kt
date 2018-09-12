package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.annotation.SuppressLint
import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.Problem
import kotlinx.android.synthetic.main.item_problems_list.*

class ProblemListViewHolder(override val containerView: View?) :
        DataListViewHolderImpl<Problem>(containerView) {

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(problem: Problem) {
        nameView.text = "${problem.index}. ${problem.name}"

        if (problem.points == null) {
            pointsView.text = itemView.context.getString(R.string.unknown)
        } else {
            pointsView.text = "${problem.points.toInt()}"
        }
    }
}