package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BaseActivity
import com.example.sergey.codeforcesapplication.feature.base.tabbed.PagerAdapter
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.MainActivityFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        setToolbarTitle(R.string.contestsList)

        val fragmentsInfo = MainActivityFactory.create(applicationContext)
        pager.adapter = PagerAdapter(supportFragmentManager).apply { addFragments(fragmentsInfo) }

        tabsLayout.setupWithViewPager(pager)
    }
}
