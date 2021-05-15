package com.example.englishvocabulary.ui.home

import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentBookmarkBinding

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {


    companion object {
        fun newInstance() =
            BookmarkFragment()
    }
}