package com.example.sergey.codeforcesapplication.feature.userInfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.extansion.hide
import com.example.sergey.codeforcesapplication.extansion.show
import com.example.sergey.codeforcesapplication.feature.base.MVPView
import com.example.sergey.codeforcesapplication.feature.base.ToolbarActivity
import com.example.sergey.codeforcesapplication.model.pojo.User
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info_item_main_info.*
import kotlinx.android.synthetic.main.activity_user_info_item_rating_info.*
import kotlinx.android.synthetic.main.activity_user_info_item_social_info.*
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

interface UserInfoActivityView : MVPView {
    fun hideAll()
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
    fun showUserInfo(user: User)
}

class UserInfoActivity : ToolbarActivity(), UserInfoActivityView {

    private lateinit var userHandler: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        restoreData(savedInstanceState)

        setToolbarTitle(userHandler)
        showBackAction()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putString(USER_HANDLER_EXTRA, userHandler)
        }
    }

    override fun hideAll() {
        hideProgress()
        hideError()
        userInfoContainer.hide()
    }

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showError() = noConnectionView.show()

    override fun hideError() = noConnectionView.hide()

    @SuppressLint("SetTextI18n")
    override fun showUserInfo(user: User) {
        // Основное
        firstNameView.text = user.firstName ?: getString(R.string.unknown)
        lastNameView.text = user.lastName ?: getString(R.string.unknown)
        userCountryView.text = user.country ?: getString(R.string.unknown)
        userCityView.text = user.city ?: getString(R.string.unknown)

        val date = Date(TimeUnit.MILLISECONDS.convert(user.registrationTimeSeconds, TimeUnit.SECONDS))
        val dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
        userRegistrationView.text = dateFormatter.format(date)

        // Социальное
        userEmailView.text = user.email ?: getText(R.string.unknown)
        userVkIdView.text = user.vkId ?: getText(R.string.unknown)
        userOpenIdView.text = user.openId ?: getText(R.string.unknown)
        userFriendsOfCountView.text = user.friendOfCount.toString()

        // Рейтинг
        userContributionView.text = user.contribution.toString()
        userCurrentRatingView.text = "${user.rank} (${user.rating})"
        userMaxRatingView.text = "${user.maxRank} (${user.maxRating})"

        userInfoContainer.show()
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        userHandler = savedInstanceState?.getString(USER_HANDLER_EXTRA)
                ?: intent.getStringExtra(USER_HANDLER_EXTRA)
    }

    companion object {
        private const val USER_HANDLER_EXTRA = "user_handler"

        fun start(context: Context, user: User) {
            val intent = Intent(context, UserInfoActivity::class.java).apply {
                putExtra(USER_HANDLER_EXTRA, user.handle)
            }
            context.startActivity(intent)
        }
    }
}