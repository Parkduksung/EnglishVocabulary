package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(
    app: Application,
    private val excelVocaRepository: ExcelVocaRepository
) :
    BaseViewModel(app), LifecycleObserver {

    private val _allExcelData = MutableLiveData<List<ExcelData>>()
    val allExcelData: LiveData<List<ExcelData>> = _allExcelData

    fun getAllExcelVoca(day: String) {
        excelVocaRepository.getWantDayExcelData(day.toLowerCase()) {
            val getAllExcelData = it.map { excelVocaEntity -> excelVocaEntity.toExcelData() }
            _allExcelData.value = getAllExcelData
        }
    }

    fun toggleBookmark(isBookmarked: Boolean, item: ExcelData) {
        excelVocaRepository.toggleBookmarkExcelData(isBookmarked, item) {
        }
    }
}