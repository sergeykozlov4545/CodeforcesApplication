package com.example.sergey.codeforcesapplication.feature.base

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extension.hide
import com.example.sergey.codeforcesapplication.extension.show

interface ProcessingDataContainer<T> {
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