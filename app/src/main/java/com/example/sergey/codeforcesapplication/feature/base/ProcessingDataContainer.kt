package com.example.sergey.codeforcesapplication.feature.base

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extension.hide
import com.example.sergey.codeforcesapplication.extension.show
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapter

interface ProcessingDataContainer<T> {
    fun initView(bundle: Bundle?)
    fun showProgress()
    fun hideProgress()
    fun showStatusMessage(message: String)
    fun showData(data: T)
}

abstract class ProcessingDataContainerImpl<T>(parent: ViewGroup) : ProcessingDataContainer<T> {

    private val progressView: View = parent.findViewById(R.id.progressView)
    private val statusMessageView: TextView = parent.findViewById(R.id.statusMessageView)

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showStatusMessage(message: String) {
        statusMessageView.text = message
        statusMessageView.show()
    }
}

class ProcessingListDataContainerImpl<T>(parent: ViewGroup) :
        ProcessingDataContainerImpl<List<T>>(parent) {

    private val dataListView: RecyclerView = parent.findViewById(R.id.dataListView)
    private lateinit var adapter: DataListAdapter<T>

    override fun initView(bundle: Bundle?) {
        val countColumns = bundle?.getInt(COUNT_COLUMNS_EXTRA, 1) ?: 1
        val visibleDividers = bundle?.getBoolean(VISIBLE_DIVIDERS_EXTRA, false) ?: false
        val backgroundColor = bundle?.getInt(BACKGROUND_COLOR_EXTRA, 0) ?: 0

        dataListView.apply {
            adapter = this@ProcessingListDataContainerImpl.adapter as RecyclerView.Adapter<*>
            layoutManager = if (countColumns == 1) {
                LinearLayoutManager(context.applicationContext)
            } else {
                GridLayoutManager(context.applicationContext, countColumns)
            }
            if (backgroundColor != 0) {
                setBackgroundColor(backgroundColor)
            }
            if (visibleDividers) {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
            }
        }
    }

    override fun showData(data: List<T>) {
        adapter.updateData(data)
        dataListView.show()
    }

    fun setAdapter(adapter: DataListAdapter<T>) {
        this.adapter = adapter
    }

    companion object {
        const val COUNT_COLUMNS_EXTRA = "count_columns"
        const val VISIBLE_DIVIDERS_EXTRA = "visible_dividers"
        const val BACKGROUND_COLOR_EXTRA = "background_color"
    }
}