package com.example.englishvocabulary.ui.home

import android.os.Bundle
import android.view.View
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() =
            SearchFragment()
    }
}