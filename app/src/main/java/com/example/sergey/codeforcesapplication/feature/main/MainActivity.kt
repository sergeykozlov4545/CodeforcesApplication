package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.tabbed.TabbedActivity
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.FragmentInfo
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.MainActivityFactory

class MainActivity : TabbedActivity() {

    override val fragmentsInfo: List<FragmentInfo>
        get() = MainActivityFactory.create(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarTitle(R.string.contestsList)
    }
}
