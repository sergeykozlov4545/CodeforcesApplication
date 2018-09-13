package com.example.sergey.codeforcesapplication.feature.userInfo.fragment

import android.os.Bundle
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView
import com.example.sergey.codeforcesapplication.feature.userInfo.UserInfoActivity
import com.example.sergey.codeforcesapplication.model.pojo.User

interface UserInfoFragmentView : ProcessingView<User> {
    fun getUserHandler(): String
}

class UserInfoFragment : ProcessingFragment<User, UserInfoFragmentView>(), UserInfoFragmentView {
    private lateinit var userHandler: String

    override val processingContainer by lazy { UserInfoContainerFactory.create(context!!) }
    override val presenter by lazy { UserInfoPresenterFactory.create(context!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userHandler = arguments?.getString(UserInfoActivity.USER_HANDLER_EXTRA) ?: run {
            activity?.finish()
            return@run ""
        }
    }

    override fun getUserHandler() = userHandler

    companion object {
        fun create(userHandler: String) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putString(UserInfoActivity.USER_HANDLER_EXTRA, userHandler)
            }
        }
    }
}