package com.example.sergey.codeforcesapplication.feature.helper

import android.graphics.drawable.Drawable
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R

class ToolbarHelper(private val toolbar: Toolbar) {

    private val titleView: TextView = toolbar.findViewById(R.id.title_toolbar)

    init {
        titleView.isSelected = true
        toolbar.contentInsetStartWithNavigation = 0
        toolbar.contentInsetEndWithActions = 0
    }

    fun setTitle(text: String) {
        titleView.text = text
    }

    fun updateBackAction(drawable: Drawable, onActionClick: (view: View) -> Unit) {
        toolbar.navigationIcon = drawable
        toolbar.setNavigationOnClickListener(onActionClick)

        titleView.setOnClickListener(onActionClick)
    }
}