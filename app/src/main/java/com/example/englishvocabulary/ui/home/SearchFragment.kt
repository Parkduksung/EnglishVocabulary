package com.example.englishvocabulary.ui.home

import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {


    companion object {
        fun newInstance() =
            SearchFragment()
    }
}