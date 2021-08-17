package com.example.englishvocabulary.ui.home.quiz

import android.app.Application
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class QuizViewModel(
    app: Application
) : BaseViewModel(app) {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)


    //전체 데이터를 가져와 무작위로 섞고 4개에 1쌍인 리스트를 10개로 묶은 결과를 얻는 메서드
    fun getQuizList() {
        viewModelMainScope.launch {
            when (val result = excelVocaRepository.getAllExcelData()) {
                is Result.Success -> {
                    val quizList =
                        result.value.map { it.toExcelData() }.shuffled().chunked(4).subList(0, 10)

                    viewStateChanged(QuizViewState.QuizList(quizList))
                }

                is Result.Failure -> {
                    viewStateChanged(QuizViewState.Error(result.throwable.message!!))
                }
            }
        }
    }

    sealed class QuizViewState : ViewState {
        data class QuizList(val quizList: List<List<ExcelData>>) : QuizViewState()
        data class Error(val errorMessage: String) : QuizViewState()
    }

}