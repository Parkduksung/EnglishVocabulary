package com.example.englishvocabulary.viewmodel

import android.app.Application
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.interactor.StudyInteractor
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel(app: Application) : BaseViewModel(app) {

    private val studyInteractor by inject(StudyInteractor::class.java)

    // 즐겨찾기 On/Off
    fun toggleBookmark(item: ExcelData) {
        viewModelMainScope.launch {
            when (val bookmarkResult = studyInteractor.toggleBookmarkExcelData(item)) {
                is Result.Success -> {
                    if (bookmarkResult.value.like) {
                        viewStateChanged(HomeViewState.AddBookmark(item))
                    } else {
                        viewStateChanged(HomeViewState.DeleteBookmark(item))
                    }
                }
                is Result.Failure -> {
                    viewStateChanged(HomeViewState.Error("Bookmark Error"))
                }
            }
        }
    }

    sealed class HomeViewState : ViewState {
        data class Error(val errorMessage: String) : HomeViewState()
        data class AddBookmark(val excelData: ExcelData) : HomeViewState()
        data class DeleteBookmark(val excelData: ExcelData) : HomeViewState()
    }

}