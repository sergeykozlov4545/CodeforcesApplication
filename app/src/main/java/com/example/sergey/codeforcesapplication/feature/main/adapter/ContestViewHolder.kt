package com.example.sergey.codeforcesapplication.feature.main.adapter

import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extension.format
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.android.synthetic.main.item_contest_list.*
import java.util.*
import java.util.concurrent.TimeUnit

class ContestViewHolder(
        override val containerView: View?,
        private val itemClick: (Contest) -> Unit
) : DataListViewHolderImpl<Contest>(containerView) {

    override fun bindViewHolder(contest: Contest) {
        contestNameView.text = contest.name
        contestStartView.text = if (contest.startTimeSeconds != null) {
            startTimeFormatter(contest.startTimeSeconds)
        } else {
            itemView.context.getString(R.string.unknown)
        }
        contestDurationView.text = durationFormatter(contest.durationSeconds)

        contestCardView.setOnClickListener { itemClick.invoke(contest) }
    }

    private fun startTimeFormatter(seconds: Long): String {
        return Date(TimeUnit.MILLISECONDS.convert(seconds, TimeUnit.SECONDS)).format()
    }

    private fun durationFormatter(seconds: Long): String {
        val sb = StringBuilder()

        val h = seconds / 3600
        val m = seconds % 3600 / 60
        if (h < 10) {
            sb.append("0")
        }
        sb.append(h).append(":")
        if (m < 10) {
            sb.append("0")
        }
        sb.append(m)

        return sb.toString()
    }
}