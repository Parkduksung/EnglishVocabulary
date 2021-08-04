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

    private val studyInteractor by inject(StudyInteractor::class.java)

    // 다른페이지에서 다시 돌아올때 갱신되게끔 하기 위해 생명주기를 onResume 에 불리게끔 설정.
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getAllBookmark() {
        excelVocaRepository.getAllBookmarkExcelData { bookmarkEntityList ->
            _bookmarkListLiveData.value = bookmarkEntityList.toSet().map { it.toExcelData() }
        }
    }

    // 즐겨찾기 삭제
    fun deleteBookmark(item: ExcelData) {
        viewModelMainScope.launch {
            if(studyInteractor.toggleBookmarkExcelData(false, item)){
                viewStateChanged(BookmarkViewState.RenewBookmarkAdapter(item))
            }
        }
    }

    sealed class BookmarkViewState : ViewState {
        data class RenewBookmarkAdapter(val excelData: ExcelData) : BookmarkViewState()
    }
}