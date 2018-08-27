package com.example.sergey.codeforcesapplication.feature.base

interface ViewWithProccesing<T> : MVPView {
    fun showProgress()
    fun hideProgress()
    fun showEmptyListMessage()
    fun showDataList(values: List<T>)
}