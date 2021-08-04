package com.example.englishvocabulary.ui.home.bookmark

import android.app.Application
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.ui.home.study.StudyInteractor
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class BookmarkViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    private val _bookmarkListLiveData = MutableLiveData<List<ExcelData>>()
    val bookmarkListLiveData: LiveData<List<ExcelData>> = _bookmarkListLiveData

    fun getAllBookmark() {
        excelVocaRepository.getAllBookmarkExcelData { bookmarkEntityList ->
            _bookmarkListLiveData.value = bookmarkEntityList.toSet().map { it.toExcelData() }
        }
    }

}