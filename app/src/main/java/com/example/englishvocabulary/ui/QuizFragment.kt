package com.example.englishvocabulary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseFragment
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.databinding.FragmentQuizBinding
import com.example.englishvocabulary.viewmodel.HomeViewModel
import com.example.englishvocabulary.ui.adapter.QuizAdapter
import com.example.englishvocabulary.ui.adapter.viewholder.QuizItemListener
import com.example.englishvocabulary.viewmodel.QuizViewModel

class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz), QuizItemListener {

    private val quizViewModel by viewModels<QuizViewModel>()

    private val quizAdapter by lazy { QuizAdapter() }

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun getItem(item: ExcelData) {
        homeViewModel.toggleBookmark(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initViewModel()

    }

    private fun initUi() {
        startQuizAdapter()
    }

    private fun initViewModel() {
        quizViewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState: ViewState? ->
            (viewState as? QuizViewModel.QuizViewState)?.let { onChangedViewState(it) }
        }
    }

    private fun onChangedViewState(viewState: QuizViewModel.QuizViewState) {
        when (viewState) {

            is QuizViewModel.QuizViewState.Error -> {
                Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            is QuizViewModel.QuizViewState.QuizList -> {
                quizAdapter.addAllVocaData(viewState.quizList)
            }

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
            quizViewModel.getQuizList()
        }

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
}