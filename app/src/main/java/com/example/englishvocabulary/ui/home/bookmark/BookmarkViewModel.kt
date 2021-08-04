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

    fun getAllBookmark(adapter: BookmarkAdapter) {
        excelVocaRepository.getAllBookmarkExcelData { bookmarkEntityList ->
            adapter.addAllBookmarkData(bookmarkEntityList.toSet().map { it.toExcelData() })
        }
    }

}