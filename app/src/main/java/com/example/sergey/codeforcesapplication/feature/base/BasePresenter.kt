package com.example.sergey.codeforcesapplication.feature.base

abstract class BasePresenter<MVPView> : MVPPresenter<MVPView> {

    private var view: MVPView? = null

    override fun attachView(view: MVPView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun getView() = view
}