package com.example.englishvocabulary.ui.home.study

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentStudyDetailBinding
import com.example.englishvocabulary.ui.home.adapter.DetailAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.VocaListener

class StudyDetailFragment :
    BaseFragment<FragmentStudyDetailBinding>(R.layout.fragment_study_detail), VocaListener {

    private val studyViewModel by viewModels<StudyViewModel>(ownerProducer = { requireParentFragment() })

    private val detailAdapter by lazy { DetailAdapter() }

    private val receiveBundleDay by lazy { requireArguments().getString(KEY_DAY) }

    override fun getItemClick(isChecked: Boolean, item: ExcelData) {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = studyViewModel

        initUi()
        initViewModel()

    }

    private fun initUi() {
        binding.dayTv.text = receiveBundleDay
        studyViewModel.getAllExcelVoca(receiveBundleDay)
        startDetailAdapter()
    }

    private fun initViewModel() {
        studyViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState ->
            if (viewState is StudyViewModel.StudyViewState.ExcelVoca) {
                detailAdapter.addAllVocaData(viewState.wandData)
            }
        }
    }

    private fun startDetailAdapter() {

        binding.studyDetailRv.run {
            adapter = detailAdapter
            detailAdapter.setVocaItemClickListener(this@StudyDetailFragment)
        }

    }


    companion object {

        private const val KEY_DAY = "key_day"

        fun newInstance(detailDay: String): StudyDetailFragment =
            StudyDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_DAY, detailDay)
                }
            }

    }
}