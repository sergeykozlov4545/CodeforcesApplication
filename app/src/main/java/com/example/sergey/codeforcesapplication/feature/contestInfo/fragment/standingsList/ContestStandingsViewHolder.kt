package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.annotation.SuppressLint
import android.view.View
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.RankListRow
import kotlinx.android.synthetic.main.item_stangings_list.*

class ContestStandingsViewHolder(override val containerView: View?) :
        DataListViewHolderImpl<RankListRow>(containerView) {

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(rankListRow: RankListRow) {
        rankView.text = "${rankListRow.rank}."
        membersView.text = rankListRow.party.members.joinToString { it.handle }
        pointsView.text = rankListRow.points.toInt().toString()
    }
}