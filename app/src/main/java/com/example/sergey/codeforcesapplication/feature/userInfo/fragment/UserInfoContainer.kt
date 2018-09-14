package com.example.sergey.codeforcesapplication.feature.userInfo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extension.show
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainerImpl
import com.example.sergey.codeforcesapplication.model.pojo.User

class UserInfoContainer(private val ratingClick: (User) -> Unit) : ProcessingDataContainerImpl<User>() {
    private var userInfoContainer: LinearLayout? = null

    //Аватар
    private var avatarView: ImageView? = null

    // Основное
    private var firstNameView: TextView? = null
    private var lastNameView: TextView? = null
    private var userCountryView: TextView? = null
    private var userCityView: TextView? = null

    // Социальное
    private var userEmailView: TextView? = null
    private var userVkIdView: TextView? = null
    private var userOpenIdView: TextView? = null
    private var userFriendsOfCountView: TextView? = null

    // Рейтинг
    private var userContributionView: TextView? = null
    private var userCurrentRatingView: TextView? = null
    private var userMaxRatingView: TextView? = null
    private var userRatingDetailsContainer: CardView? = null

    override fun initView(parent: View, bundle: Bundle?) {
        super.initView(parent, bundle)
        userInfoContainer = parent.findViewById(R.id.userInfoContainer)
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
        userInfoContainer = null
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
        userInfoContainer?.show()
    }

    private fun showMainUserInfo(user: User) {
        firstNameView?.text = user.firstName ?: context!!.getString(R.string.unknown)
        lastNameView?.text = user.lastName ?: context!!.getString(R.string.unknown)
        userCountryView?.text = user.country ?: context!!.getString(R.string.unknown)
        userCityView?.text = user.city ?: context!!.getString(R.string.unknown)
    }

    private fun showSocialUserInfo(user: User) {
        userEmailView?.text = user.email ?: context!!.getText(R.string.unknown)
        userVkIdView?.text = user.vkId ?: context!!.getText(R.string.unknown)
        userOpenIdView?.text = user.openId ?: context!!.getText(R.string.unknown)
        userFriendsOfCountView?.text = user.friendOfCount.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun showRatingUserInfo(user: User) {
        userContributionView?.text = user.contribution.toString()
        userCurrentRatingView?.text = "${user.rank} (${user.rating})"
        userMaxRatingView?.text = "${user.maxRank} (${user.maxRating})"
        userRatingDetailsContainer?.setOnClickListener { ratingClick.invoke(user) }
    }
}