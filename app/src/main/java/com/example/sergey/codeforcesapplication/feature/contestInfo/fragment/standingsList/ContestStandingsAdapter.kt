package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapterImpl
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow

class ContestStandingsAdapter(private val itemClick: (RankListRow) -> Unit) : DataListAdapterImpl<RankListRow>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolderImpl<RankListRow> {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_stangings_list, parent, false)
        return ContestStandingsViewHolder(view, itemClick)
    }
}