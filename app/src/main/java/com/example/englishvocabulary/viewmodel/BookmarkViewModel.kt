package com.example.englishvocabulary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    app: Application,
    private val excelVocaRepository: ExcelVocaRepository
) : BaseViewModel(app), LifecycleObserver {

    private val _bookmarkListLiveData = MutableLiveData<List<ExcelData>>()
    val bookmarkListLiveData: LiveData<List<ExcelData>> = _bookmarkListLiveData


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getAllBookmark() {
        excelVocaRepository.getAllBookmarkExcelData { bookmarkEntityList ->
            _bookmarkListLiveData.value = bookmarkEntityList.toSet().map { it.toExcelData() }
        }
    }

    fun deleteBookmark(item: ExcelData) {
        excelVocaRepository.toggleBookmarkExcelData(false, item) {
            if (it) {
                viewStateChanged(BookmarkViewState.RenewBookmarkAdapter)
            }
        }
    }

    sealed class BookmarkViewState : ViewState {
        object RenewBookmarkAdapter : BookmarkViewState()
    }
}