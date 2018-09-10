package com.example.sergey.codeforcesapplication.feature.base.presenter

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