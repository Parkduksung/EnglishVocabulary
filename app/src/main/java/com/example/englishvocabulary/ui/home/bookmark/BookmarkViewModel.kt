package com.example.englishvocabulary.ui.home.bookmark

import android.app.Application
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.ui.home.adapter.BookmarkAdapter
import com.example.englishvocabulary.ui.home.study.StudyInteractor
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class BookmarkViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    fun getAllBookmark() {
        viewModelMainScope.launch {
            excelVocaRepository.getAllBookmarkExcelData { bookmarkEntityList ->
                val toBookmarkList = bookmarkEntityList.toSet().map { it.toExcelData() }
                viewStateChanged(BookmarkViewState.BookmarkList(toBookmarkList))
            }
        }
    }

    sealed class BookmarkViewState : ViewState {
        data class BookmarkList(val bookmarkList: List<ExcelData>) : BookmarkViewState()
    }


}