package com.example.sergey.codeforcesapplication.feature.ratingInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.BACKGROUND_COLOR_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl.Companion.VISIBLE_DIVIDERS_EXTRA
import com.example.sergey.codeforcesapplication.feature.base.activity.ProcessingListActivity
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.model.pojo.RatingChange

interface RatingInfoView : ProcessingListView<RatingChange> {
    val userHandler: String
}

class RatingInfoActivity : ProcessingListActivity<RatingChange, RatingInfoView>(), RatingInfoView {

    override val processingContainer by lazy { RatingInfoContainerFactory.create(this) }
    override val presenter by lazy { RatingInfoPresenterFactory.create(this) }
    override lateinit var userHandler: String

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
                putExtra(DATA_CONTAINER_ARGUMENTS_BUNDLE_EXTRA, Bundle().apply {
                    putInt(BACKGROUND_COLOR_EXTRA, android.R.color.white)
                    putBoolean(VISIBLE_DIVIDERS_EXTRA, true)
                })
                putExtra(USER_HANDLER_EXTRA, userHandler)
            })
        }
    }
}