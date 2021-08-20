package com.example.englishvocabulary.viewmodel

import android.app.Application
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class QuizViewModel(
    app: Application
) : BaseViewModel(app) {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)


    fun getQuizList() {
        viewModelMainScope.launch {
            when (val result = excelVocaRepository.getAllExcelData()) {
                is Result.Success -> {
                    viewStateChanged(QuizViewState.QuizList(shuffledAndChunkedList(result.value)))
                }

                is Result.Failure -> {
                    viewStateChanged(QuizViewState.Error(result.throwable.message!!))
                }
            }
        }
    }

    //전체 데이터를 가져와 무작위로 섞고 4개에 1쌍인 리스트를 10개로 묶은 결과를 얻는 메서드
    private fun shuffledAndChunkedList(list: List<ExcelVocaEntity>): List<List<ExcelData>> =
        list.map { it.toExcelData() }.shuffled().chunked(4).subList(0, 10)

    sealed class QuizViewState : ViewState {
        data class QuizList(val quizList: List<List<ExcelData>>) : QuizViewState()
        data class Error(val errorMessage: String) : QuizViewState()
    }

}