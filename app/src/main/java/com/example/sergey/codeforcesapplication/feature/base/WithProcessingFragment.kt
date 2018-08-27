package com.example.sergey.codeforcesapplication.feature.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extansion.hide
import com.example.sergey.codeforcesapplication.extansion.show
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapter

abstract class WithProcessingFragment<T> : Fragment(), ViewWithProccesing<T> {

    private lateinit var progressView: View
    protected lateinit var emptyListView: TextView
    private lateinit var dataListView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_with_processing, container, false)

        progressView = view.findViewById(R.id.progressView)
        emptyListView = view.findViewById(R.id.emptyListView)
        dataListView = view.findViewById(R.id.dataListView)

        dataListView.apply {
            adapter = getDataListAdapter() as RecyclerView.Adapter<*>
            layoutManager = getDataListLayoutManager()
        }

        return view
    }

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showEmptyListMessage() {
        emptyListView.text = getEmptyListMessageText()
    }

    override fun showDataList(values: List<T>) {
        getDataListAdapter().updateData(values)
        dataListView.show()
    }

    abstract fun getEmptyListMessageText(): String

    abstract fun getDataListLayoutManager(): RecyclerView.LayoutManager

    abstract fun getDataListAdapter(): DataListAdapter<T>
}