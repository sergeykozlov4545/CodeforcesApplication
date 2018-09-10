package com.example.sergey.codeforcesapplication.feature.base

interface ViewWithProcessing<T> : MVPView {
    fun hideAll()
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
    fun showEmptyListMessage()
    fun showDataList(values: List<T>)
}