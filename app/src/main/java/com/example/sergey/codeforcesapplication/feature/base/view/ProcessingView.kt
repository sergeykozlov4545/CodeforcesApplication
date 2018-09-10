package com.example.sergey.codeforcesapplication.feature.base.view

interface ProcessingView<T> : MVPView {
    fun onSuccessOperation(data: T)
    fun onErrorOperation(message: String)
    fun onError()
}