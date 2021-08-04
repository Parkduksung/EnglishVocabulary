package com.example.englishvocabulary.ui.home.quiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.App
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentQuizBinding
import com.example.englishvocabulary.ui.home.HomeViewModel
import com.example.englishvocabulary.ui.home.adapter.QuizAdapter
import com.example.englishvocabulary.ui.home.adapter.viewholder.QuizItemListener

class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz), QuizItemListener {

    private val quizViewModel by viewModels<QuizViewModel>()

    private val quizAdapter by lazy { QuizAdapter() }

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun getItem(item: ExcelData) {
        homeViewModel.toggleBookmark(isBookmarked = true, item)
        Toast.makeText(requireContext(), "즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startQuizAdapter()

        quizViewModel.quizList.observe(viewLifecycleOwner, {
            quizAdapter.addAllVocaData(it)
        })


        binding.startTv.setOnClickListener {
            toggleContentAndQuiz(isStart = true)
        }

        binding.backButton.setOnClickListener {
            toggleContentAndQuiz(isStart = false)
        }

        binding.refreshButton.setOnClickListener {
            startQuizAdapter()
        }

    }

    private fun toggleContentAndQuiz(isStart: Boolean) {
        binding.quizContainer.isVisible = isStart
        binding.startContainer.isVisible = !isStart
    }

    companion object {
        fun newInstance() =
            QuizFragment()
    }


    private fun startQuizAdapter() {
        binding.quizRv.run {
            adapter = quizAdapter
            quizAdapter.clear()
            layoutManager = LinearLayoutManager(requireContext())
            quizAdapter.setVocaItemClickListener(this@QuizFragment)
            quizViewModel.getAllExcelVoca()
        }
    }
}