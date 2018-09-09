package com.example.sergey.codeforcesapplication.feature.base

import com.example.sergey.codeforcesapplication.model.remote.Response
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface ProcessingPresenter<T, View : ViewWithProcessing<T>> : MVPPresenter<View> {
    fun loadFunction(): Deferred<Response<T>>
    fun dataMap(data: T): T
}

abstract class ProcessingPresenterImpl<T, View : ViewWithProcessing<T>> :
        BasePresenter<View>(), ProcessingPresenter<T, View> {

    override fun viewIsReady() {
        launch(UI) {
            try {
                getView()?.hideAll()
                getView()?.showProgress()
                val response = loadFunction().await()
                getView()?.hideProgress()

                if (response.isSuccess) {
                    getView()?.showData(dataMap(response.result))
                } else {
                    getView()?.showErrorOperation(response.comment!!)
                }
            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}

abstract class ProcessingListDataPresenterImpl<T, View : ListViewWithProcessing<T>> :
        ProcessingPresenterImpl<List<T>, View>() {

    override fun viewIsReady() {
        launch(UI) {
            try {
                getView()?.hideAll()
                getView()?.showProgress()
                val response = loadFunction().await()
                getView()?.hideProgress()

                if (!response.isSuccess) {
                    getView()?.showEmptyListMessage()
                    getView()?.showErrorOperation(response.comment!!)
                    return@launch
                }

                if (response.result.isEmpty()) {
                    getView()?.showEmptyListMessage()
                    return@launch
                }

                getView()?.showData(dataMap(response.result))
            } catch (e: Exception) {
                getView()?.hideProgress()
                getView()?.showError()
            }
        }
    }
}