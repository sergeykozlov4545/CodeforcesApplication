package com.example.sergey.codeforcesapplication.feature.ratingInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.activity.BaseActivity

class RatingInfoActivity : BaseActivity() {

    private lateinit var userHandler: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_info)

        userHandler = savedInstanceState?.getString(USER_HANDLER_EXTRA)
                ?: intent.getStringExtra(USER_HANDLER_EXTRA)

        showBackAction()
        setToolbarTitle(getString(R.string.activityRatingInfoTitle, userHandler))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.apply {
            putString(USER_HANDLER_EXTRA, userHandler)
        }
    }

    companion object {
        private const val USER_HANDLER_EXTRA = "user_handler"

        fun start(context: Context, userHandler: String) {
            context.startActivity(Intent(context, RatingInfoActivity::class.java).apply {
                putExtra(USER_HANDLER_EXTRA, userHandler)
            })
        }
    }
}