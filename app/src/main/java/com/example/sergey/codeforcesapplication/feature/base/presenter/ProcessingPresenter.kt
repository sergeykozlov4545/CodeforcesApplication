package com.example.sergey.codeforcesapplication.feature.base.presenter

import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView
import com.example.sergey.codeforcesapplication.model.remote.Response
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface ProcessingPresenter<T, View : ProcessingView<T>> : MVPPresenter<View> {
    fun loadData()
    fun loadedFunction(): Deferred<Response<T>>
    fun prepareData(data: T) = data
}

abstract class ProcessingPresenterImpl<T, View : ProcessingView<T>> :
        BasePresenter<View>(), ProcessingPresenter<T, View> {

    override fun loadData() {
        launch(UI) {
            try {
                val response = loadedFunction().await()

                if (response.isSuccess) {
                    getView()?.onSuccessOperation(prepareData(response.result!!))
                    return@launch
                }

                getView()?.onErrorOperation(response.comment)

            } catch (e: Exception) {
                getView()?.onError()
            }
        }
    }
}

interface ProcessingListPresenter<T, View : ProcessingListView<T>> :
        ProcessingPresenter<List<T>, View> {

    val emptyListMessage: Int
}

abstract class ProcessingListPresenterImpl<T, View : ProcessingListView<T>> :
        BasePresenter<View>(), ProcessingListPresenter<T, View> {

    override fun loadData() {
        launch(UI) {
            try {
                val response = loadedFunction().await()

                if (response.isSuccess) {
                    val data = response.result ?: emptyList()
                    val preparedData = prepareData(data)

                    if (preparedData.isEmpty()) {
                        getView()?.onEmptyData(emptyListMessage)
                    } else {
                        getView()?.onSuccessOperation(preparedData)
                    }
                    return@launch
                }

                getView()?.onErrorOperation(response.comment)

            } catch (e: Exception) {
                getView()?.onError()
            }
        }
    }
}