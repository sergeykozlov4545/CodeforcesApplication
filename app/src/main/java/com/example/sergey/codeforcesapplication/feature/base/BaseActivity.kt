package com.example.sergey.codeforcesapplication.feature.base

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.helper.ToolbarHelper
import kotlinx.android.synthetic.main.toolbar.*

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private val toolbarHelper: ToolbarHelper by lazy { ToolbarHelper(toolbar) }

    protected fun setToolbarTitle(textId: Int) = setToolbarTitle(getString(textId))

    protected fun setToolbarTitle(text: String) = toolbarHelper.setTitle(text)

    protected fun showBackAction() {
        val icon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp) ?: return
        toolbarHelper.updateBackAction(icon) {
            onBackPressed()
        }
    }
}