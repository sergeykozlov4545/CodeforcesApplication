package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.tabbed.TabbedActivity
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.MainActivityTabsFactory
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.TabInfo

class MainActivity : TabbedActivity() {

    override val tabsInfo: List<TabInfo>
        get() = MainActivityTabsFactory.create(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarTitle(R.string.contestsList)
    }
}
