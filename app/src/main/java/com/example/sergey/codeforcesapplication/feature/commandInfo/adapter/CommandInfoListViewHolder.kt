package com.example.sergey.codeforcesapplication.feature.commandInfo.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.sergey.codeforcesapplication.feature.base.adapter.DataListViewHolderImpl
import com.example.sergey.codeforcesapplication.model.pojo.User
import kotlinx.android.synthetic.main.item_members_list.*

class CommandInfoListViewHolder(override val containerView: View?) :
        DataListViewHolderImpl<User>(containerView) {

    override fun bindViewHolder(user: User) {
        Glide.with(itemView.context)
                .load("https:${user.avatarUrl}")
                .into(avatarView)

        handleView.text = user.handle
        nameView.text = user.fullName

        itemView.setOnClickListener { view ->
            // TODO: Открыть экран детализации пользователя
        }
    }
}