package com.example.sergey.codeforcesapplication.feature.userInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.activity.ProcessingActivity
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView
import com.example.sergey.codeforcesapplication.model.pojo.User


interface UserInfoView : ProcessingView<User> {
    val userHandler: String
}

class UserInfoActivity : ProcessingActivity<User, UserInfoView>(), UserInfoView {
    override val processingContainer by lazy { UserInfoContainerFactory.create(this) }
    override val presenter by lazy { UserInfoPresenterFactory.create(this) }
    override lateinit var userHandler: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        restoreData(savedInstanceState)
        showBackAction()
        setToolbarTitle(userHandler)
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
        const val USER_HANDLER_EXTRA = "user_handler"

        fun start(context: Context, userHandler: String) {
            context.startActivity(
                    Intent(context, UserInfoActivity::class.java).apply {
                        putExtra(USER_HANDLER_EXTRA, userHandler)
                    }
            )
        }
    }
}