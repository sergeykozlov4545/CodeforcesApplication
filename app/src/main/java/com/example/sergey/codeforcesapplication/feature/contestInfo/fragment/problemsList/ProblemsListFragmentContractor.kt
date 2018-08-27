package com.example.sergey.codeforcesapplication.feature.contestInfo.fragment.problemsList

import com.example.sergey.codeforcesapplication.feature.base.MVPPresenter
import com.example.sergey.codeforcesapplication.feature.base.MVPView

interface ProblemsListFragmentContractor {
    interface View : MVPView

    interface Presenter<View> : MVPPresenter<View>
}