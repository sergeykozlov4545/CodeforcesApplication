package com.example.sergey.codeforcesapplication.feature.main

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingListFragment
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingListView
import com.example.sergey.codeforcesapplication.feature.main.presenter.CurrentContestsPresenterFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.PastContestsPresenterFactory
import com.example.sergey.codeforcesapplication.feature.main.presenter.UpcommingContestsPresenterFactory
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface ContestsFragmentView : ProcessingListView<Contest>

abstract class ContestsFragment :
        ProcessingListFragment<Contest, ContestsFragmentView>(), ContestsFragmentView {

    protected val processingContainerView by lazy {
        view!!.findViewById<ViewGroup>(R.id.processingContainer)!!
    }

    companion object {
        // TODO: использовать KTX
        fun getArguments(context: Context) = Bundle().apply {
            val columnCount = context.resources.getInteger(R.integer.list_column_count)
            putInt(ProcessingListDataContainerImpl.COUNT_COLUMNS_EXTRA, columnCount)
        }
    }
}

class UpcommingContestsFragment : ContestsFragment() {
    override val processingContainer by lazy {
        UpcommingContestsDataContainerFactory.create(processingContainerView)
    }

    override val presenter by lazy { UpcommingContestsPresenterFactory.create(context!!) }

    companion object {
        fun create(context: Context) = UpcommingContestsFragment().apply {
            arguments = ContestsFragment.getArguments(context)
        }
    }
}

class CurrentContestsFragment : ContestsFragment() {
    override val processingContainer by lazy {
        CurrentContestsDataContainerFactory.create(processingContainerView)
    }

    override val presenter by lazy { CurrentContestsPresenterFactory.create(context!!) }

    companion object {
        fun create(context: Context) = CurrentContestsFragment().apply {
            arguments = ContestsFragment.getArguments(context)
        }
    }
}

class PastContestsFragment : ContestsFragment() {
    override val processingContainer by lazy {
        PastContestsDataContainerFactory.create(processingContainerView)
    }

    override val presenter by lazy { PastContestsPresenterFactory.create(context!!) }

    companion object {
        fun create(context: Context) = PastContestsFragment().apply {
            arguments = ContestsFragment.getArguments(context)
        }
    }
}