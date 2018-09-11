package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingDataContainer
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.ContestsDataContainerFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.CurrentContestsPresenterFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.PastContestsPresenterFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.UpcommingContestsPresenterFactory
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface ContestsFragmentView : ProcessingListView<Contest>

abstract class ContestsFragment : ProcessingListFragment<Contest, ContestsFragmentView>(), ContestsFragmentView {

    override val processingContainer: ProcessingDataContainer<List<Contest>> by lazy {
        ContestsDataContainerFactory.create(view!!.findViewById(R.id.processingContainer))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.processing_view, container, false)
    }
}

class UpcommingContestsFragment : ContestsFragment() {
    override val presenter: ProcessingPresenter<List<Contest>, ContestsFragmentView> by lazy {
        UpcommingContestsPresenterFactory.create(context!!)
    }
}

class CurrentContestsFragment : ContestsFragment() {
    override val presenter: ProcessingPresenter<List<Contest>, ContestsFragmentView> by lazy {
        CurrentContestsPresenterFactory.create(context!!)
    }
}

class PastContestsFragment : ContestsFragment() {
    override val presenter: ProcessingPresenter<List<Contest>, ContestsFragmentView> by lazy {
        PastContestsPresenterFactory.create(context!!)
    }
}