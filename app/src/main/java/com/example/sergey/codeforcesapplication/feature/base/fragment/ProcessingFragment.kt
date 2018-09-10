package com.example.sergey.codeforcesapplication.feature.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainer
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.BACKGROUND_COLOR_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.COUNT_COLUMNS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.VISIBLE_DIVIDERS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView

class ProcessingFragment<T> : Fragment(), ProcessingView<T> {

    private lateinit var processingContainer: ProcessingDataContainer<T>
    private lateinit var presenter: ProcessingPresenter<T, ProcessingView<T>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processingContainer.initView(arguments)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
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

    private fun setProcessingContainer(processingContainer: ProcessingDataContainer<T>) {
        this.processingContainer = processingContainer
    }

    private fun setPresenter(presenter: ProcessingPresenter<T, ProcessingView<T>>) {
        this.presenter = presenter
    }

    companion object {
        fun <T> getInstance(
                countColumns: Int = 1,
                visibleDividers: Boolean = false,
                backgroundColor: Int = 0,
                processingContainer: ProcessingDataContainer<T>,
                presenter: ProcessingPresenter<T, ProcessingView<T>>
        ): Fragment {
            return ProcessingFragment<T>().apply {
                // TODO: использовать KTX
                arguments = Bundle().apply {
                    putInt(COUNT_COLUMNS_EXTRA, countColumns)
                    putBoolean(VISIBLE_DIVIDERS_EXTRA, visibleDividers)
                    putInt(BACKGROUND_COLOR_EXTRA, backgroundColor)
                }
                setProcessingContainer(processingContainer)
                setPresenter(presenter)
            }
        }
    }
}