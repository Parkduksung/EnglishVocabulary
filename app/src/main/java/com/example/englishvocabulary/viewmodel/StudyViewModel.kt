package com.example.englishvocabulary.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.interactor.BookmarkInteractor
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class StudyViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val bookmarkInteractor by inject(BookmarkInteractor::class.java)

    fun getAllExcelVoca(day: String?) {
        viewModelMainScope.launch {
            when (val result = bookmarkInteractor.getWantExcelVocaData(wantDay = day)) {
                is Result.Success -> {
                    viewStateChanged(StudyViewState.ExcelVoca(result.value.map { it.toExcelData() }))
                }
                is Result.Failure -> {
                    viewStateChanged(StudyViewState.Error(result.throwable.message!!))
                }
            }
        }
    }

    fun routeDetail(day: String) {
        viewModelMainScope.launch {
            viewStateChanged(StudyViewState.RouteDetail(day))
        }
    }

    fun routeContent() {
        viewModelMainScope.launch {
            viewStateChanged(StudyViewState.RouteContent)
        }
    }

    fun toggleBookmark(excelData: ExcelData) {
        viewModelMainScope.launch {
            viewStateChanged(StudyViewState.ToggleBookmark(excelData))
        }
    }

    fun addBookmark(excelData: ExcelData) {
        viewModelMainScope.launch {
            viewStateChanged(StudyViewState.AddBookmark(excelData))
        }
    }

    fun deleteBookmark(excelData: ExcelData) {
        viewModelMainScope.launch {
            viewStateChanged(StudyViewState.DeleteBookmark(excelData))
        }
    }


    sealed class StudyViewState : ViewState {
        data class Error(val errorMessage: String) : StudyViewState()
        data class ExcelVoca(val wandData: List<ExcelData>) : StudyViewState()
        data class RouteDetail(val day: String) : StudyViewState()
        data class ToggleBookmark(val excelData: ExcelData) : StudyViewState()
        data class AddBookmark(val excelData: ExcelData) : StudyViewState()
        data class DeleteBookmark(val excelData: ExcelData) : StudyViewState()
        object RouteContent : StudyViewState()
    }
}