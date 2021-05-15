package com.example.englishvocabulary.ui.home

import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentQuizBinding

class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {


    companion object {
        fun newInstance() =
            QuizFragment()
    }
}