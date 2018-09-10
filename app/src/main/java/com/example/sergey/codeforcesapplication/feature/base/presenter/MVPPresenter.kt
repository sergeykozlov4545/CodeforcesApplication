package com.example.sergey.codeforcesapplication.feature.base.presenter

import com.example.sergey.codeforcesapplication.feature.base.view.MVPView

interface MVPPresenter<View : MVPView> {
    fun attachView(view: View)
    fun detachView()
    fun getView(): View?
}