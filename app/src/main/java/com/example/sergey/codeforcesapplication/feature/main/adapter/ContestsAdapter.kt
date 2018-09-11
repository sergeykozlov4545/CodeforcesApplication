package com.example.sergey.codeforcesapplication.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapterImpl
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.Contest

class ContestsAdapter : DataListAdapterImpl<Contest>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolderImpl<Contest> {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contest_list, parent, false)

        return ContestViewHolder(view)
    }
}