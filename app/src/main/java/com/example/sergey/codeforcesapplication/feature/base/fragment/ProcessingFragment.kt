package com.example.sergey.codeforcesapplication.feature.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainer
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView

abstract class ProcessingFragment<T, V : ProcessingView<T>> : Fragment(), ProcessingView<T> {

    abstract val processingContainer: ProcessingDataContainer<T>
    abstract val presenter: ProcessingPresenter<T, V>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.processing_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processingContainer.initView(arguments)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onResume() {
        super.onResume()
        presenter.attachView(this as V)
        processingContainer.showProgress()
        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun onSuccessOperation(data: T) {
        processingContainer.hideProgress()
        processingContainer.showData(data)
    }

    override fun onErrorOperation(message: String) {
        processingContainer.hideProgress()
        processingContainer.showStatusMessage(message)
    }

    override fun onError() {
        processingContainer.hideProgress()
        processingContainer.showStatusMessage(getString(R.string.noConnectionServer))
    }
}

abstract class ProcessingListFragment<T, V : ProcessingListView<T>> :
        ProcessingFragment<List<T>, V>(), ProcessingListView<T> {

    override fun onEmptyData(messageId: Int) {
        processingContainer.hideProgress()
        processingContainer.showStatusMessage(getString(messageId))
    }
}