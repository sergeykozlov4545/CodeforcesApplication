package com.example.sergey.codeforcesapplication.feature.base.tabbed

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.example.sergey.codeforcesapplication.feature.base.BaseActivity

@SuppressLint("Registered")
abstract class TabbedActivity : BaseActivity() {

    abstract val fragmentsInfo: Map<Fragment, String>

    abstract val tabLayout: TabLayout
    abstract val viewPager: ViewPager

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val adapter = PagerAdapter(supportFragmentManager)
        fragmentsInfo.forEach { adapter.addFragment(it.key, it.value) }
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}