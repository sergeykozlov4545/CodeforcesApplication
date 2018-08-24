package com.example.sergey.codeforcesapplication.feature.contestInfo

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import kotlinx.android.synthetic.main.toolbar.*

class ContestInfoActivity : AppCompatActivity() {

    private var contestId: Long = 0
    private lateinit var contestName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_info)

        restoreData(savedInstanceState)

        initToolbar()
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        contestId = intent.getLongExtra(CONTEST_ID_EXTRA, 0)
        contestName = intent.getStringExtra(CONTEST_NAME_EXTRA)
    }

    private fun initToolbar() {
        (toolbar.findViewById<TextView>(R.id.title_toolbar)).text = contestName
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        const val CONTEST_ID_EXTRA = "contest_id"
        const val CONTEST_NAME_EXTRA = "contest_name"
    }
}