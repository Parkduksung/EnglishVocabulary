package com.example.englishvocabulary.ui.home.study

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.databinding.FragmentStudyMainBinding
import com.example.englishvocabulary.ui.home.HomeViewModel


class StudyFragment : BaseFragment<FragmentStudyMainBinding>(R.layout.fragment_study_main) {

    private val studyViewModel by viewModels<StudyViewModel>()

    private val homeViewModel by activityViewModels<HomeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

    }

    private fun initViewModel() {
        lifecycle.addObserver(studyViewModel)

        studyViewModel.viewStateLiveData.observe(requireActivity()) { viewState: ViewState? ->
            (viewState as? StudyViewModel.StudyViewState)?.let { onChangedViewState(viewState) }
        }

        homeViewModel.viewStateLiveData.observe(requireActivity()) { viewState: ViewState? ->
            (viewState as? HomeViewModel.HomeViewState)?.let { homeViewState ->
                when (homeViewState) {
                    is HomeViewModel.HomeViewState.AddBookmark -> {
                        studyViewModel.addBookmark(homeViewState.excelData)
                    }
                    is HomeViewModel.HomeViewState.DeleteBookmark -> {
                        studyViewModel.deleteBookmark(homeViewState.excelData)
                    }
                }
            }
        }
    }


    private fun onChangedViewState(viewState: StudyViewModel.StudyViewState) {
        when (viewState) {
            is StudyViewModel.StudyViewState.Error -> {
                Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_LONG).show()
            }

            is StudyViewModel.StudyViewState.RouteDetail -> {
                routeDetailFragment(viewState.day)
            }

            is StudyViewModel.StudyViewState.RouteContent -> {
                routeContentFragment()
            }

            is StudyViewModel.StudyViewState.ToggleBookmark -> {
                homeViewModel.toggleBookmark(viewState.excelData)
            }
        }
    }

    private fun routeDetailFragment(day: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.study_container, StudyDetailFragment.newInstance(detailDay = day))
            .commit()
    }

    private fun routeContentFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.study_container, StudyContentFragment())
            .commit()
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
