package com.example.sergey.codeforcesapplication.feature.userInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.activity.ProcessingFragmentActivity
import com.example.sergey.codeforcesapplication.feature.userInfo.fragment.UserInfoFragment

class UserInfoActivity : ProcessingFragmentActivity() {
    override val fragment: Fragment
        get() = UserInfoFragment.create(userHandler)

    private lateinit var userHandler: String

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