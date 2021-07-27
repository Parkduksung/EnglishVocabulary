package com.example.englishvocabulary.ui.home.study

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import org.koin.java.KoinJavaComponent.inject

class StudyViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    private val _allExcelData = MutableLiveData<List<ExcelData>>()
    val allExcelData: LiveData<List<ExcelData>> = _allExcelData

    // 날짜에 따른 ExcelVoca 얻어오기.
    fun getAllExcelVoca(day: String) {
        excelVocaRepository.getWantDayExcelData(day.toLowerCase()) {
            val getAllExcelData = it.map { excelVocaEntity -> excelVocaEntity.toExcelData() }
            _allExcelData.value = getAllExcelData
        }
    }

    // 즐겨찾기 on/off
    fun toggleBookmark(isBookmarked: Boolean, item: ExcelData) {
        excelVocaRepository.toggleBookmarkExcelData(isBookmarked, item) {
        }
    }
}