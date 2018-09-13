package com.example.sergey.codeforcesapplication.feature.commandInfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListAdapterImpl
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.User

class CommandInfoListAdapter(private val itemClick: (User) -> Unit) : DataListAdapterImpl<User>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolderImpl<User> {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_members_list, parent, false)
        return CommandInfoListViewHolder(view, itemClick)
    }
}