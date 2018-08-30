package com.example.sergey.codeforcesapplication.feature.ratingDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ToolbarActivity

class RatingDetailsActivity : ToolbarActivity() {

    private lateinit var userHandler: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_details)

        restoreData(savedInstanceState)

        showBackAction()
        setToolbarTitle(getString(R.string.activityRatingDetailsTitle, userHandler))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putString(USER_HANDLER_EXTRA, userHandler)
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        userHandler = savedInstanceState?.getString(USER_HANDLER_EXTRA)
                ?: intent.getStringExtra(USER_HANDLER_EXTRA)
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