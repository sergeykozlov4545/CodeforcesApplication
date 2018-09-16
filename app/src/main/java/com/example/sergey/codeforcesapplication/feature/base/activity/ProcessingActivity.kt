package com.example.sergey.codeforcesapplication.feature.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainer
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView

@SuppressLint("Registered")
abstract class ProcessingActivity<T, V : ProcessingView<T>> : BaseActivity(), ProcessingView<T> {

    abstract val processingContainer: ProcessingDataContainer<T>
    abstract val presenter: ProcessingPresenter<T, V>

    private var arguments: Bundle? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        arguments = savedInstanceState?.getParcelable(DATA_CONTAINER_ARGUMENTS_BUNDLE_EXTRA)
                ?: intent.getParcelableExtra(DATA_CONTAINER_ARGUMENTS_BUNDLE_EXTRA)

        processingContainer.initView(findViewById<View>(R.id.processingContainer), arguments)
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

    override fun onStop() {
        super.onStop()
        processingContainer.destroyView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.apply {
            putParcelable(DATA_CONTAINER_ARGUMENTS_BUNDLE_EXTRA, arguments)
        }
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

    companion object {
        const val DATA_CONTAINER_ARGUMENTS_BUNDLE_EXTRA = "arguments_bundle"
    }
}

abstract class ProcessingListActivity<T, V : ProcessingListView<T>> :
        ProcessingActivity<List<T>, V>(), ProcessingListView<T> {

    override fun onEmptyData(messageId: Int) {
        processingContainer.hideProgress()
        processingContainer.showStatusMessage(getString(messageId))
    }
}