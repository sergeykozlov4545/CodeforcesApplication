package com.example.sergey.codeforcesapplication.feature.base

interface ViewWithProcessing<T> : MVPView {
    fun hideAll()
    fun showProgress()
    fun hideProgress()
    fun showEmptyListMessage()
    fun showDataList(values: List<T>)
    fun showErrorOperation(message: String)
    fun showError()
}