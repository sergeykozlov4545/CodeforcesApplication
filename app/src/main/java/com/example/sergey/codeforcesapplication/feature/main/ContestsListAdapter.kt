package com.example.sergey.codeforcesapplication.feature.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.model.pojo.Contest

class ContestsListAdapter : RecyclerView.Adapter<ContestsListViewHolder>() {
    private val contests: MutableList<Contest> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestsListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contest_list, parent, false)

        return ContestsListViewHolder(view)
    }

    override fun getItemCount(): Int = contests.size

    override fun onBindViewHolder(holder: ContestsListViewHolder, position: Int) {
        if (position < contests.size) {
            holder.bindViewHolder(contests[position])
        }
    }

    fun updateData(contests: List<Contest>) {
        this.contests.clear()
        this.contests.addAll(contests)
        notifyDataSetChanged()
    }
}