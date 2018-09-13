package com.example.sergey.codeforcesapplication.feature.base.tabbed

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BaseActivity
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.TabInfo

@SuppressLint("Registered")
abstract class TabbedActivity : BaseActivity() {

    private var tabPosition: Int = 0
    abstract val tabsInfo: List<TabInfo>

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        restoreData(savedInstanceState)
        initTabs()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putInt(TAB_POSITION_EXTRA, tabPosition)
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        tabPosition = savedInstanceState?.getInt(TAB_POSITION_EXTRA, 0) ?: 0
    }

    private fun initTabs() {
        val tabLayout = findViewById<TabLayout>(R.id.tabsLayout)
        tabsInfo.forEach { info ->
            tabLayout.addTab(tabLayout.newTab().apply {
                text = info.tabTitle
            })
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition = tab?.position ?: return
                showContentTab(tabPosition)
            }
        })

        if (tabPosition == 0) {
            showContentTab(tabPosition)
        } else {
            tabLayout.getTabAt(tabPosition)?.select()
        }
    }

    private fun showContentTab(tabPosition: Int) {
        showFragment(tabsInfo[tabPosition].fragment)
    }

    private fun showFragment(fragment: Fragment) {
        // TODO: Использовать KTX
        supportFragmentManager.beginTransaction().apply {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            replace(R.id.content, fragment)
        }.commitNowAllowingStateLoss()
    }

    companion object {
        private const val TAB_POSITION_EXTRA = "tab_position"
    }
}