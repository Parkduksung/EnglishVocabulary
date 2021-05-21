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

    //전체 데이터를 가져와 무작위로 섞고 4개에 1쌍인 리스트를 10개로 묶은 결과를 얻는 메서드
    fun getAllExcelVoca() {
        excelVocaRepository.getExcelData { excelVocaEntityList ->
            val getAllExcelData = excelVocaEntityList.map { it.toExcelData() }.shuffled()
            _quizList.value = getAllExcelData.chunked(4).subList(0, 10)
        }
    }

    // 롱클릭시 즐겨찾기 추가.
    fun addBookmarkItem(item: ExcelData) {
        excelVocaRepository.toggleBookmarkExcelData(toggleBookmark = true, item) {
            if(it){
                Toast.makeText(App.instance.context(), "즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

}