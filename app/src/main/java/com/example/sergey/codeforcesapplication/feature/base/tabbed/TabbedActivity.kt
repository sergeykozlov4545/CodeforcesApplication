package com.example.sergey.codeforcesapplication.feature.base.tabbed

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.BaseActivity
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.FragmentInfo

@SuppressLint("Registered")
abstract class TabbedActivity : BaseActivity() {

    private lateinit var tabLayout: TabLayout

    private var tabPosition: Int = 0
    abstract val tabsInfo: List<FragmentInfo>

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        restoreData(savedInstanceState)

        tabLayout = findViewById(R.id.tabsLayout)
        tabsInfo.forEach { info ->
            tabLayout.addTab(tabLayout.newTab().apply {
                text = info.fragmentTitle
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
            selectTab(tabPosition)
        }
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

    private fun selectTab(tabPosition: Int) {
        tabLayout.getTabAt(tabPosition)?.select()
    }

    private fun showContentTab(tabPosition: Int) {
        showFragment(tabsInfo[tabPosition].fragment)
    }

    private fun showFragment(fragment: Fragment) {
        // TODO: Использовать KTX
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, fragment)
        }.commitAllowingStateLoss()
    }

    companion object {
        private const val TAB_POSITION_EXTRA = "tab_position"
    }
}