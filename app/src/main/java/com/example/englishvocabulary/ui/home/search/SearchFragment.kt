package com.example.englishvocabulary.ui.home.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = searchViewModel

        searchViewModel.translateWordLiveData.observe(viewLifecycleOwner, {
            binding.translateTv.text = it
        })
    }

    companion object {
        fun newInstance() =
            SearchFragment()
    }
}