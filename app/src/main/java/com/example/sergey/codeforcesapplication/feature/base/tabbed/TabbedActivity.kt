package com.example.sergey.codeforcesapplication.feature.base.tabbed

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BaseActivity
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.TabInfo

@SuppressLint("Registered")
abstract class TabbedActivity : BaseActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var contentPager: ViewPager

    abstract val tabsInfo: List<TabInfo>

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        contentPager = findViewById(R.id.contentPager)
        contentPager.adapter = PagerAdapter(supportFragmentManager, tabsInfo)
        tabLayout = findViewById(R.id.tabsLayout)
        tabLayout.setupWithViewPager(contentPager)
    }
}