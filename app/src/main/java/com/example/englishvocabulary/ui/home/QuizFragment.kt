package com.example.englishvocabulary.ui.home

import android.os.Bundle
import android.view.View
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentQuizBinding

class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() =
            QuizFragment()
    }
}