package com.example.sergey.codeforcesapplication.feature.base.adapter

import android.support.v7.widget.RecyclerView

interface DataListAdapter<T> {
    fun updateData(values: List<T>)
}

abstract class DataListAdapterImpl<T> : RecyclerView.Adapter<DataListViewHolderImpl<T>>(), DataListAdapter<T> {

    private val values: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: DataListViewHolderImpl<T>, position: Int) {
        if (position < values.size) {
            holder.bindViewHolder(values[position])
        }
    }

    override fun updateData(values: List<T>) {
        this.values.clear()
        this.values.addAll(values)
        notifyDataSetChanged()
    }
}