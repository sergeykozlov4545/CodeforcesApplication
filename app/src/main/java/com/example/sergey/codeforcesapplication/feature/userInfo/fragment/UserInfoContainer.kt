package com.example.sergey.codeforcesapplication.feature.userInfo.fragment

import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extension.show
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainerImpl
import com.example.sergey.codeforcesapplication.model.pojo.User

// TODO: Пофиксить отображение текста в расскрытом изображении
// TODO: Пофиксить проблему с кликом по детализации рейтинга (первый клик не проходит с рипл)
class UserInfoContainer(private val ratingClick: (User) -> Unit) : ProcessingDataContainerImpl<User>() {
    //Аватар
    private var avatarView: ImageView? = null

    // Основное
    private var firstNameView: TextureView? = null
    private var lastNameView: TextureView? = null
    private var userCountryView: TextureView? = null
    private var userCityView: TextureView? = null

    // Социальное
    private var userEmailView: TextureView? = null
    private var userVkIdView: TextureView? = null
    private var userOpenIdView: TextureView? = null
    private var userFriendsOfCountView: TextureView? = null

    // Рейтинг
    private var userContributionView: TextureView? = null
    private var userCurrentRatingView: TextureView? = null
    private var userMaxRatingView: TextureView? = null
    private var userRatingDetailsContainer: LinearLayout? = null

    override fun initView(parent: View, bundle: Bundle?) {
        super.initView(parent, bundle)
        avatarView = parent.findViewById(R.id.avatarView)

        firstNameView = parent.findViewById(R.id.firstNameView)
        lastNameView = parent.findViewById(R.id.lastNameView)
        userCountryView = parent.findViewById(R.id.userCountryView)
        userCityView = parent.findViewById(R.id.userCityView)

        userEmailView = parent.findViewById(R.id.userEmailView)
        userVkIdView = parent.findViewById(R.id.userVkIdView)
        userOpenIdView = parent.findViewById(R.id.userOpenIdView)
        userFriendsOfCountView = parent.findViewById(R.id.userFriendsOfCountView)

        userContributionView = parent.findViewById(R.id.userContributionView)
        userCurrentRatingView = parent.findViewById(R.id.userCurrentRatingView)
        userMaxRatingView = parent.findViewById(R.id.userMaxRatingView)
        userRatingDetailsContainer = parent.findViewById(R.id.userRatingDetailsContainer)
    }

    override fun destroyView() {
        super.destroyView()
        avatarView = null
        firstNameView = null
        lastNameView = null
        userCountryView = null
        userCityView = null
        userEmailView = null
        userVkIdView = null
        userOpenIdView = null
        userFriendsOfCountView = null
        userContributionView = null
        userCurrentRatingView = null
        userMaxRatingView = null
        userRatingDetailsContainer = null
    }

    override fun showData(user: User) {
        context ?: return

        val localContext = context!!
        Glide.with(localContext)
                .load("https:${user.titlePhoto}")
                .into(avatarView!!)
        avatarView?.show()


        showMainUserInfo(user)
        showSocialUserInfo(user)
        showRatingUserInfo(user)
    }

    private fun showMainUserInfo(user: User) {}
    private fun showSocialUserInfo(user: User) {}
    private fun showRatingUserInfo(user: User) {}
}