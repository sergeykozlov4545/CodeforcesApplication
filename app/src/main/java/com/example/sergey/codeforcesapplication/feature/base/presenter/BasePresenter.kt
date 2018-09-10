package com.example.sergey.codeforcesapplication.feature.base.presenter

import com.example.sergey.codeforcesapplication.feature.base.view.MVPView

open class BasePresenter<View : MVPView> : MVPPresenter<View> {

    private var view: View? = null

    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getView() = view
}