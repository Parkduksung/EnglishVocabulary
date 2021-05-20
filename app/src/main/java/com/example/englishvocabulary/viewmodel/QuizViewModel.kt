package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    app: Application,
    private val excelVocaRepository: ExcelVocaRepository
) : BaseViewModel(app) {

    private val _quizList = MutableLiveData<List<List<ExcelData>>>()
    val quizList: LiveData<List<List<ExcelData>>> = _quizList

    fun getAllExcelVoca() {
        excelVocaRepository.getExcelData { excelVocaEntityList ->
            val getAllExcelData = excelVocaEntityList.map { it.toExcelData() }
            _quizList.value = getAllExcelData.chunked(4).subList(0, 10)
        }
    }


}