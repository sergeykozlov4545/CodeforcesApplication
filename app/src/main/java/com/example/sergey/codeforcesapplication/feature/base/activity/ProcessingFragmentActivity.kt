package com.example.sergey.codeforcesapplication.feature.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sergey.codeforcesapplication.R

@SuppressLint("Registered")
abstract class ProcessingFragmentActivity : BaseActivity() {
    abstract val fragment: Fragment

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        showFragment()
    }

    private fun showFragment() {
        // TODO: Использовать KTX
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, fragment)
        }.commitNowAllowingStateLoss()
    }
}