package com.example.sergey.codeforcesapplication.feature.main

import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.feature.main.activity.MainActivityContractor
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.android.synthetic.main.item_contest_list.*
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ContestsListViewHolder(override val containerView: View?) : DataListViewHolderImpl<Contest>(containerView) {

    override fun bindViewHolder(contest: Contest) {
        contestNameView.text = contest.name
        contestStartView.text = if (contest.startTimeSeconds != null) {
            startTimeFormatter(contest.startTimeSeconds)
        } else {
            itemView.context.getString(R.string.unknown)
        }
        contestDurationView.text = durationFormatter(contest.durationSeconds)

        contestCardView.setOnClickListener { v ->
            val context = v?.context

            if (context is MainActivityContractor.View) {
                context.getPresenter().contestCardClicked(contest)
            }
        }
    }

    private fun startTimeFormatter(seconds: Long): String {
        val date = Date(TimeUnit.MILLISECONDS.convert(seconds, TimeUnit.SECONDS))
        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date)
        return dateFormat.format("dd.MM.yyyy hh:mm")
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