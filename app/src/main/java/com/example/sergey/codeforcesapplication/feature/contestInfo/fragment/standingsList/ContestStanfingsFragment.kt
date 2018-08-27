package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.standingsList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R

class ContestStanfingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contest_standings, container, false)
    }
}