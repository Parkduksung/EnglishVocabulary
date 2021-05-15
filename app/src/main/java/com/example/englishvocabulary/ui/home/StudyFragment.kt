package com.example.englishvocabulary.ui.home

import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentStudyBinding

class StudyFragment : BaseFragment<FragmentStudyBinding>(R.layout.fragment_study) {


    companion object {
        fun newInstance() =
            StudyFragment()
    }
}