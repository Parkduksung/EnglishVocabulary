package com.example.englishvocabulary.ui.home.study

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentStudyContentBinding
import com.example.englishvocabulary.ui.home.adapter.DayAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.DayListener

class StudyContentFragment :
    BaseFragment<FragmentStudyContentBinding>(R.layout.fragment_study_content), DayListener {

    private val studyViewModel by viewModels<StudyViewModel>(ownerProducer = { requireParentFragment() })

    private val dayAdapter by lazy { DayAdapter() }


    override fun clickDay(day: String) {
        studyViewModel.routeDetail(day)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startDayAdapter()
    }

    private fun startDayAdapter() {
        binding.studyContentRv.run {
            adapter = dayAdapter
            dayAdapter.setDayItemClickListener(this@StudyContentFragment)
        }
    }
}