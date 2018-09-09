package com.example.sergey.codeforcesapplication.feature.base

interface ViewWithProcessing<T> : MVPView {
    fun hideAll()
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
    fun hideError()
    fun showData(data: T)
}

interface ListViewWithProcessing<T> : ViewWithProcessing<List<T>> {
    fun showEmptyListMessage()
}