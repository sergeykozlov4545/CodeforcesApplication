package com.example.sergey.codeforcesapplication.feature.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.extansion.hide
import com.example.sergey.codeforcesapplication.extansion.show
import com.example.sergey.codeforcesapplication.feature.main.ContestsListAdapter
import com.example.sergey.codeforcesapplication.model.pojo.Contest

abstract class ContestsListFragment : Fragment(), ContestsListFragmentContractor.View {

    private lateinit var progressView: View
    protected lateinit var emptyListView: TextView
    private lateinit var contestsListView: RecyclerView

    private var contestsListAdapter = ContestsListAdapter()

    protected lateinit var presenter: ContestsListFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contests_list, container, false)

        initView(view)

        return view
    }

    override fun onResume() {
        super.onResume()

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun onPause() {
        super.onPause()

        presenter.detachView()
    }

    override fun showProgress() {
        progressView.show()
    }

    override fun hideProgress() {
        progressView.hide()
    }

    override fun showContests(contestsList: List<Contest>) {
        contestsListAdapter.updateData(contestsList)
        contestsListView.show()
    }

    private fun initView(view: View) {
        progressView = view.findViewById(R.id.progressView)
        emptyListView = view.findViewById(R.id.emptyListView)

        contestsListView = view.findViewById(R.id.contestsListView)

        with(contestsListView) {
            val columnCount = resources.getInteger(R.integer.list_column_count)
            layoutManager = GridLayoutManager(context, columnCount)
            adapter = contestsListAdapter
        }
    }
}

class UncommingContestsListFragment : ContestsListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = UncommingContestsListFragmentPresenter(repository)
    }

    override fun showEmptyListMessage() {
        emptyListView.text = getString(R.string.emptyUncommingContestsList)
        emptyListView.show()
    }
}

class CurrentContestsListFragment : ContestsListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = CurrentContestsListFragmentPresenter(repository)
    }

    override fun showEmptyListMessage() {
        emptyListView.text = getString(R.string.emptyCurrentContestsList)
        emptyListView.show()
    }
}

class PastContestsListFragment : ContestsListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (context?.applicationContext as CodeforcesApplication).contestsRepository
        presenter = PastContestsListFragmentPresenter(repository)
    }

    override fun showEmptyListMessage() {
        emptyListView.text = getString(R.string.emptyPastContestsList)
        emptyListView.show()
    }
}