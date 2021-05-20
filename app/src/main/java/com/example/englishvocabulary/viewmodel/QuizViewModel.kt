package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.App
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
            val getAllExcelData = excelVocaEntityList.map { it.toExcelData() }.shuffled()
            _quizList.value = getAllExcelData.chunked(4).subList(0, 10)
        }
    }

    fun addBookmarkItem(item: ExcelData) {
        excelVocaRepository.toggleBookmarkExcelData(toggleBookmark = true, item) {
            if(it){
                Toast.makeText(App.instance.context(), "즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

}