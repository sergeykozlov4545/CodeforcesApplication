package com.example.sergey.codeforcesapplication.feature.commandInfo

import android.content.Context
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.commandInfo.adapter.CommandInfoListAdapter
import com.example.sergey.codeforcesapplication.feature.userInfo.UserInfoActivity
import com.example.sergey.codeforcesapplication.model.pojo.User

object CommandInfoContainerFactory {
    fun create(context: Context) = ProcessingListDataContainerImpl<User>().apply {
        setAdapter(CommandInfoListAdapter { UserInfoActivity.start(context, it.handle) })
    }
}