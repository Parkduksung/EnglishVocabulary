package com.example.englishvocabulary.ui.home

import android.app.Application
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.ui.home.study.StudyInteractor
import com.example.englishvocabulary.ui.home.study.StudyViewModel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel(app: Application) : BaseViewModel(app) {

    private val studyInteractor by inject(StudyInteractor::class.java)

    // 즐겨찾기 On/Off
    fun toggleBookmark(isBookmarked: Boolean, item: ExcelData) {
        viewModelMainScope.launch {
            if (studyInteractor.toggleBookmarkExcelData(isBookmarked, item)) {
                if (isBookmarked) {
                    viewStateChanged(HomeViewState.AddBookmark(item))
                } else {
                    viewStateChanged(HomeViewState.DeleteBookmark(item))
                }
            } else {
                viewStateChanged(HomeViewState.Error("Bookmark Error"))
            }
        }
    }

    sealed class HomeViewState : ViewState {
        data class Error(val errorMessage: String) : HomeViewState()
        data class AddBookmark(val excelData: ExcelData) : HomeViewState()
        data class DeleteBookmark(val excelData: ExcelData) : HomeViewState()
    }

}