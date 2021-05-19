package com.example.englishvocabulary.ui.home.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentQuizBinding
import com.example.englishvocabulary.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    private val quizViewModel by viewModels<QuizViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        fun newInstance() =
            QuizFragment()
    }
}