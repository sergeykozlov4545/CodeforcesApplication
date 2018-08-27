package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.sergey.codeforcesapplication.R

class ProblemsListFragment : Fragment() {

    private lateinit var progressView: ProgressBar
    private lateinit var problemsListView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_problems_list, container, false)

        progressView = view.findViewById(R.id.progressView)
        problemsListView = view.findViewById(R.id.problemsListView)

        return view
    }

}