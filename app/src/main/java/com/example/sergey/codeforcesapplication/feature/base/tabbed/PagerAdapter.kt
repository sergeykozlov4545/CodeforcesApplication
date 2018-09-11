package com.example.sergey.codeforcesapplication.feature.base.tabbed

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.FragmentInfo

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val fragmentsInfo: MutableList<FragmentInfo> = ArrayList()

    override fun getItem(position: Int) = fragmentsInfo[position].fragment

    override fun getCount() = fragmentsInfo.size

    override fun getPageTitle(position: Int) = fragmentsInfo[position].fragmentTitle

    fun addFragments(fragmentsInfo: List<FragmentInfo>) {
        this.fragmentsInfo.addAll(fragmentsInfo)
    }
}