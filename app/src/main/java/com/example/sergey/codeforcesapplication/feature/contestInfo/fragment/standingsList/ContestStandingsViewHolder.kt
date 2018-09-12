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
        membersView.text = getMembersInfo(rankListRow)
        pointsView.text = rankListRow.points.toInt().toString()
        itemContainer.setOnClickListener { v ->
            // TODO: КЛИК
        }
    }

    private fun getMembersInfo(rankListRow: RankListRow): String {
        val membersString = rankListRow.party.members.joinToString { it.handle }
        return if (rankListRow.party.teamName.isNullOrEmpty()) {
            membersString
        } else {
            "${rankListRow.party.teamName}: $membersString"
        }
    }
}