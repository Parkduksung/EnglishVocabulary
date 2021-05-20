package com.example.englishvocabulary.ui.home.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.databinding.FragmentQuizBinding
import com.example.englishvocabulary.ui.home.adapter.QuizAdapter
import com.example.englishvocabulary.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    private val quizViewModel by viewModels<QuizViewModel>()

    private val quizAdapter by lazy { QuizAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizViewModel.getAllExcelVoca()


        binding.quizRv.run {
            adapter = quizAdapter
        }


        quizViewModel.quizList.observe(viewLifecycleOwner, {
            quizAdapter.addAllVocaData(it)
        })

    }

    companion object {
        fun newInstance() =
            QuizFragment()
    }
}