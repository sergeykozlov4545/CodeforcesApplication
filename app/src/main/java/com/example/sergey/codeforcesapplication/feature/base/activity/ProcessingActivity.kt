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

    private var parentId: Int = 0
    private var arguments: Bundle? = null


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        parentId = savedInstanceState?.getInt(PARENT_ID_EXTRA, 0)
                ?: intent.getIntExtra(PARENT_ID_EXTRA, 0)

        if (parentId == 0) {
            throw RuntimeException("Не передан parentId")
        }

        arguments = savedInstanceState?.getParcelable(ARGUMENTS_BUNDLE_EXTRA)
                ?: intent.getParcelableExtra(ARGUMENTS_BUNDLE_EXTRA)

        processingContainer.initView(findViewById<View>(parentId), arguments)
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
            putInt(PARENT_ID_EXTRA, parentId)
            putParcelable(ARGUMENTS_BUNDLE_EXTRA, arguments)
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
        const val PARENT_ID_EXTRA = "parent_id"
        const val ARGUMENTS_BUNDLE_EXTRA = "arguments_bundle"
    }
}

abstract class ProcessingListActivity<T, V : ProcessingListView<T>> :
        ProcessingActivity<List<T>, V>(), ProcessingListView<T> {

    override fun onEmptyData(messageId: Int) {
        processingContainer.hideProgress()
        processingContainer.showStatusMessage(getString(messageId))
    }
}