package com.example.sergey.codeforcesapplication.feature.userInfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView
import com.example.sergey.codeforcesapplication.feature.userInfo.UserInfoActivity.Companion.USER_HANDLER_EXTRA
import com.example.sergey.codeforcesapplication.model.pojo.User

interface UserInfoFragmentView : ProcessingView<User> {
    val userHandler: String
}

class UserInfoFragment : ProcessingFragment<User, UserInfoFragmentView>(), UserInfoFragmentView {
    override val processingContainer by lazy { UserInfoContainerFactory.create(context!!) }
    override val presenter by lazy { UserInfoPresenterFactory.create(context!!) }

    override val userHandler by lazy {
        arguments?.getString(USER_HANDLER_EXTRA) ?: run {
            activity?.finish()
            return@run ""
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_info, container, false)

    companion object {
        fun create(userHandler: String) = UserInfoFragment().apply {
            arguments = Bundle().apply { putString(USER_HANDLER_EXTRA, userHandler) }
        }
    }
}