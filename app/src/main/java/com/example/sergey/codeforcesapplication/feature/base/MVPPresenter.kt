package com.example.sergey.codeforcesapplication.feature.base

interface MVPPresenter<MVPView> {
    fun attachView(view: MVPView)
    fun detachView()
    fun viewIsReady()
}