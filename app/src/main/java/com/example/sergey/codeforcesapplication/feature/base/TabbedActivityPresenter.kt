package com.example.sergey.codeforcesapplication.feature.base

interface TabbedActivityPresenter<View> : MVPPresenter<View> {
    fun restoredView(tabPosition: Int)
}