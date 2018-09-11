package com.example.sergey.codeforcesapplication.feature.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.codeforcesapplication.R
import com.example.sergey.codeforcesapplication.feature.base.ProcessingListDataContainerImpl
import com.example.sergey.codeforcesapplication.feature.base.fragment.ProcessingFragment
import com.example.sergey.codeforcesapplication.feature.base.presenter.ProcessingPresenter
import com.example.sergey.codeforcesapplication.feature.base.view.ProcessingView
import com.example.sergey.codeforcesapplication.feature.main.activityFactory.ContestsDataContainerFactory
import com.example.sergey.codeforcesapplication.model.pojo.Contest

interface ContestsFragmentView : ProcessingView<List<Contest>>

class ContestsFragment : ProcessingFragment<List<Contest>, ContestsFragmentView>(), ContestsFragmentView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.processing_view, container, false)
        setProcessingContainer(ContestsDataContainerFactory.create(view.findViewById(R.id.processingContainer)))
        return view
    }

    companion object {
        fun getInstance(
                countColumns: Int = 1,
                visibleDividers: Boolean = false,
                backgroundColor: Int = 0,
                presenter: ProcessingPresenter<List<Contest>, ContestsFragmentView>
        ): Fragment {
            return ContestsFragment().apply {
                // TODO: использовать KTX
                arguments = Bundle().apply {
                    putInt(ProcessingListDataContainerImpl.COUNT_COLUMNS_EXTRA, countColumns)
                    putBoolean(ProcessingListDataContainerImpl.VISIBLE_DIVIDERS_EXTRA, visibleDividers)
                    putInt(ProcessingListDataContainerImpl.BACKGROUND_COLOR_EXTRA, backgroundColor)
                }
                setPresenter(presenter)
            }
        }
    }
}