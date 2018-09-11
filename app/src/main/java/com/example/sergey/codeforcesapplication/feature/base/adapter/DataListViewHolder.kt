package com.example.sergey.codeforcesapplication.feature.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

interface DataListViewHolder<T> {
    fun bindViewHolder(value: T)
}

abstract class DataListViewHolderImpl<T>(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer,
        DataListViewHolder<T>