package com.example.sergey.codeforcesapplication.feature.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainer
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView

open class ProcessingFragment<T, V: ProcessingView<T>> : Fragment(), ProcessingView<T> {

    private lateinit var processingContainer: ProcessingDataContainer<T>
    private lateinit var presenter: ProcessingPresenter<T, V>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processingContainer.initView(arguments)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this as V)
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

    protected fun setProcessingContainer(processingContainer: ProcessingDataContainer<T>) {
        this.processingContainer = processingContainer
    }

    protected fun setPresenter(presenter: ProcessingPresenter<T, V>) {
        this.presenter = presenter
    }
}