package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.BookmarkRepository
import com.example.englishvocabulary.network.room.entity.BookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    app: Application,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel(app), LifecycleObserver {

    private val _bookmarkListLiveData = MutableLiveData<List<ExcelData>>()
    val bookmarkListLiveData: LiveData<List<ExcelData>> = _bookmarkListLiveData.distinctUntilChanged()

    private val bookmarkList = mutableSetOf<ExcelData>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun getAllBookmark() {
        bookmarkRepository.getAllList { bookmarkEntityList ->
            _bookmarkListLiveData.value = bookmarkEntityList.toSet().map { it.toExcelData() }
        }
    }


    fun getAddBookmark() {
        bookmarkRepository.addBookmark(
            BookmarkEntity(0, "day1", "resume", "이력서", true)
        ) { isSuccess ->
            if (isSuccess) {
                getAllBookmark()
//                bookmarkList.add(ExcelData("day1", "resume", "이력서", true))
                Log.d("결과", "등록됨..")
            }
        }
    }

    fun deleteBookmark(item: ExcelData) {

        bookmarkRepository.deleteBookmark(
            item.toBookmarkEntity()
        ) { isSuccess ->
            if (isSuccess) {
                getAllBookmark()
                Log.d("결과", "등록됨..")
            }
        }

    }

}