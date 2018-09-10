package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.tabbed.TabbedActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : TabbedActivity() {
    override val fragmentsInfo: Map<Fragment, String>
        get() {
            val map: MutableMap<Fragment, String> = HashMap()
            map.apply {
                put(ContestsFragment(), getString(R.string.upcomingContests))
                put(ContestsFragment(), getString(R.string.currentContests))
                put(ContestsFragment(), getString(R.string.pastContests))
            }

            return map
        }

    override val tabLayout: TabLayout
        get() = tabsLayout

    override val viewPager: ViewPager
        get() = pager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbarTitle(R.string.contestsList)
    }
}
