package com.example.sergey.codeforcesapplication.feature.base.tabbed

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.TabInfo

class PagerAdapter(
        fragmentManager: FragmentManager,
        private val tabsInfo: List<TabInfo>
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = tabsInfo[position].fragment
    override fun getCount() = tabsInfo.size
    override fun getPageTitle(position: Int) = tabsInfo[position].tabTitle
}