package com.example.sergey.codeforcesapplication.feature.ratingDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.application.CodeforcesApplication
import com.example.sergey.codeforcesapplication.extansion.hide
import com.example.sergey.codeforcesapplication.extansion.show
import com.example.sergey.codeforcesapplication.feature.base.ToolbarActivity
import com.example.sergey.codeforcesapplication.feature.base.ViewWithProcessing
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange
import kotlinx.android.synthetic.main.fragment_with_processing.*

interface RatingDetailsActivityView : ViewWithProcessing<RatingChange> {
    fun getUserHandler(): String
}

class RatingDetailsActivity : ToolbarActivity(), RatingDetailsActivityView {

    private lateinit var userHandler: String

    private val dataListAdapter = RatingChangesListAdapter()

    private lateinit var presenter: RatingChangesListActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_details)

        restoreData(savedInstanceState)
        initView()
        initPresenter()
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putString(USER_HANDLER_EXTRA, userHandler)
        }
    }

    override fun hideAll() {
        hideProgress()
        noConnectionView.hide()
        emptyListView.hide()
        dataListView.hide()
    }

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showEmptyListMessage() = emptyListView.show()

    override fun showDataList(values: List<RatingChange>) {
        dataListAdapter.updateData(values)
        dataListView.show()
    }

    override fun showError() = noConnectionView.show()

    override fun showErrorOperation(message: String) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun getUserHandler() = userHandler

    private fun restoreData(savedInstanceState: Bundle?) {
        userHandler = savedInstanceState?.getString(USER_HANDLER_EXTRA)
                ?: intent.getStringExtra(USER_HANDLER_EXTRA)
    }

    private fun initView() {
        showBackAction()
        setToolbarTitle(getString(R.string.activityRatingDetailsTitle, userHandler))

        emptyListView.text = getString(R.string.ratingChangeListIsEmpty)

        dataListView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dataListAdapter
            setBackgroundColor(ContextCompat.getColor(dataListView.context, android.R.color.white))
            addItemDecoration(DividerItemDecoration(dataListView.context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun initPresenter() {
        val repository = (applicationContext as CodeforcesApplication).contestsRepository
        presenter = RatingChangesListActivityPresenterImpl(repository)
    }

    companion object {
        private const val USER_HANDLER_EXTRA = "user_handler"

        fun start(context: Context, userHandler: String) {
            val intent = Intent(context, RatingDetailsActivity::class.java).apply {
                putExtra(USER_HANDLER_EXTRA, userHandler)
            }
            context.startActivity(intent)
        }
    }
}