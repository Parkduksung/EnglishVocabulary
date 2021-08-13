package com.example.englishvocabulary.ui.home.bookmark

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class BookmarkViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    fun getAllBookmark() {
        viewModelMainScope.launch {
            when (val resultBookmarkList = excelVocaRepository.getAllBookmarkList()) {
                is Result.Success -> {
                    viewStateChanged(BookmarkViewState.BookmarkList(resultBookmarkList.value.map { it.toExcelData() }))
                }

                is Result.Failure -> {
                    viewStateChanged(BookmarkViewState.Error(resultBookmarkList.throwable.message!!))
                }
            }
        }
    }

    sealed class BookmarkViewState : ViewState {
        data class BookmarkList(val bookmarkList: List<ExcelData>) : BookmarkViewState()
        data class Error(val errorMessage: String) : BookmarkViewState()
    }


}