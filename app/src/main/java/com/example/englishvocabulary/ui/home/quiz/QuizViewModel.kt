package com.example.englishvocabulary.ui.home.quiz

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.App
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.ui.home.study.StudyInteractor
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class QuizViewModel(
    app: Application
) : BaseViewModel(app) {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    private val _quizList = MutableLiveData<List<List<ExcelData>>>()
    val quizList: LiveData<List<List<ExcelData>>> = _quizList

    //전체 데이터를 가져와 무작위로 섞고 4개에 1쌍인 리스트를 10개로 묶은 결과를 얻는 메서드
    fun getAllExcelVoca() {
        excelVocaRepository.getExcelData { excelVocaEntityList ->
            val getAllExcelData = excelVocaEntityList.map { it.toExcelData() }.shuffled()
            _quizList.value = getAllExcelData.chunked(4).subList(0, 10)
        }
    }

}