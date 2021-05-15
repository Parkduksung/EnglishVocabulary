package com.example.englishvocabulary.ui.home

import android.os.Bundle
import android.view.View
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentBookmarkBinding

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    companion object {
        fun newInstance() =
            BookmarkFragment()
    }
}