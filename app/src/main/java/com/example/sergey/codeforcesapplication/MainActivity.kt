package com.example.sergey.codeforcesapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (toolbar.findViewById<TextView>(R.id.title_toolbar)).setText(R.string.contestsList)
    }
}
