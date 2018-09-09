package com.example.sergey.codeforcesapplication.feature.base

interface ViewWithProcessing<T> : MVPView {
    fun hideAll()
    fun showProgress()
    fun hideProgress()
    fun showData(data: T)
    fun showErrorOperation(message: String)
    fun showError()
}

interface ListViewWithProcessing<T> : ViewWithProcessing<List<T>> {
    fun showEmptyListMessage()
}