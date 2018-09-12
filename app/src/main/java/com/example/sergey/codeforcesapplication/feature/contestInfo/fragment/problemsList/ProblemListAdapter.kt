package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapterImpl
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.Problem

class ProblemListAdapter : DataListAdapterImpl<Problem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolderImpl<Problem> {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_problems_list, parent, false)

        return ProblemListViewHolder(view)
    }
}