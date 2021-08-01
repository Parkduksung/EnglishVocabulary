package com.example.englishvocabulary.ui.home.study

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentStudyBinding
import com.example.englishvocabulary.ui.home.adapter.DayAdapter
import com.example.englishvocabulary.ui.home.adapter.StudyAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.DayListener
import com.example.englishvocabulary.ui.home.adapter.viewholder.VocaListener
import com.example.englishvocabulary.ui.home.bookmark.RenewBookmarkListener
import com.example.englishvocabulary.ui.splash.SplashViewModel


class StudyFragment : BaseFragment<FragmentStudyBinding>(R.layout.fragment_study), VocaListener,
    DayListener, RenewBookmarkListener {

    private val studyAdapter by lazy { StudyAdapter() }

    private val dayAdapter by lazy { DayAdapter() }

    private val studyViewModel by viewModels<StudyViewModel>()


    override fun renewItem(item: ExcelData) {
        if (binding.studyRv.adapter == studyAdapter) {
            studyViewModel.toggleBookmark(!item.like, item)
            studyAdapter.stateChangeBookmark(item)
        }
    }

    override fun getItemClick(isChecked: Boolean, item: ExcelData) {
        studyViewModel.toggleBookmark(isChecked, item)
        studyAdapter.stateChangeBookmark(item)
    }

    override fun getItemClick(item: String) {
        binding.contentTv.text = item
        binding.contentContainer.isVisible = true
        studyViewModel.getAllExcelVoca(day = item)
        startStudyAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        startDayAdapter()

        binding.backButton.setOnClickListener {
            binding.contentContainer.isVisible = false
            startDayAdapter()
        }

    }

    private fun initViewModel() {
        lifecycle.addObserver(studyViewModel)

        studyViewModel.viewStateLiveData.observe(requireActivity()) { viewState: ViewState? ->
            (viewState as? StudyViewModel.StudyViewState)?.let { onChangedViewState(viewState) }
        }

    }

    private fun onChangedViewState(viewState: StudyViewModel.StudyViewState) {
        when (viewState) {
            is StudyViewModel.StudyViewState.Error -> {
                Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_LONG).show()
            }
            is StudyViewModel.StudyViewState.ExcelVoca -> {
                studyAdapter.addAllVocaData(viewState.wandData)
            }
        }
    }


    private fun startDayAdapter() {
        binding.studyRv.run {
            adapter = dayAdapter
            dayAdapter.clear()
            layoutManager = GridLayoutManager(requireContext(), 2)
            dayAdapter.setDayItemClickListener(this@StudyFragment)
            dayAdapter.addAllDayData()
        }
    }

    private fun startStudyAdapter() {
        binding.studyRv.run {
            adapter = studyAdapter
            studyAdapter.clear()
            layoutManager = LinearLayoutManager(requireContext())
            studyAdapter.setVocaItemClickListener(this@StudyFragment)
        }
    }

    override fun onDestroyView() {
        lifecycle.removeObserver(studyViewModel)
        super.onDestroyView()
    }

    companion object {
        fun newInstance() =
            StudyFragment()
    }
}
