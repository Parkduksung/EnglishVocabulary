package com.example.englishvocabulary.ui.home.study

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class StudyViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    // 날짜에 따른 ExcelVoca 얻어오기.
    fun getAllExcelVoca(day: String) {

        viewModelIOScope.launch {
            when (val result = excelVocaRepository.getWantDayExcelVocaData(wantDay = day.toLowerCase())) {
                is Result.Success -> {
                    viewStateChanged(StudyViewState.ExcelVoca(result.value.map { it.toExcelData() }))
                }
                is Result.Failure -> {
                    viewStateChanged(StudyViewState.Error(result.throwable.message!!))
                }
            }
        }
    }

    // 즐겨찾기 on/off
    fun toggleBookmark(isBookmarked: Boolean, item: ExcelData) {
        excelVocaRepository.toggleBookmarkExcelData(isBookmarked, item) {
        }
    }


    sealed class StudyViewState : ViewState {
        data class Error(val errorMessage: String) : StudyViewState()
        data class ExcelVoca(val wandData: List<ExcelData>) : StudyViewState()
    }
}