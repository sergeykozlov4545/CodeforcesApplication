package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R

class ContestsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contests, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ContestsFragment", "onCreate: ${this}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ContestsFragment", "onDestroy: ${this}")
    }
}