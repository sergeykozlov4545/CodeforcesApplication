package com.example.sergey.codeforcesapplication.feature.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.model.pojo.Contest
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_contest_list.*
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ContestsListViewHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindViewHolder(contest: Contest) {
        contestNameView.text = contest.name
        contestStartView.text = if (contest.startTimeSeconds != null) {
            startTimeFormatter(contest.startTimeSeconds)
        } else {
            itemView.context.getString(R.string.contest_start_unknown)
        }
        contestDurationView.text = durationFormatter(contest.durationSeconds)

        contestCardView.setOnClickListener(null)
        if (!contest.isUpcomming) {
            contestCardView.setOnClickListener { v ->
                val context = v?.context ?: return@setOnClickListener

                if (context is MainActivityContractor.MainActivityView) {
                    context.getPresenter().contestCardClicked(contest.id)
                }
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