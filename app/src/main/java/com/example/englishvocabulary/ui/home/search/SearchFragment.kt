package com.example.englishvocabulary.ui.home.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {

        binding.viewModel = searchViewModel

        searchViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState: ViewState? ->
            (viewState as? SearchViewModel.SearchViewstate)?.let { onChangedViewState(it) }
        }
    }

    private fun onChangedViewState(viewState: SearchViewModel.SearchViewstate) {
        when (viewState) {
            is SearchViewModel.SearchViewstate.Translate -> {
                binding.translateTv.text = viewState.translateWord
            }

            is SearchViewModel.SearchViewstate.Error -> {
                Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        fun newInstance() =
            SearchFragment()
    }
}