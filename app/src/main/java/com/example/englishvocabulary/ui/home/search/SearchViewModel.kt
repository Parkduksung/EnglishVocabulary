package com.example.englishvocabulary.ui.home.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.repository.SearchRepository
import org.koin.java.KoinJavaComponent.inject

class SearchViewModel(
    app: Application
) :
    BaseViewModel(app) {

    private val searchRepository by inject(SearchRepository::class.java)

    val searchWordLiveData = MutableLiveData<String>()

    private val _translateWordLiveData = MutableLiveData<String>()
    val translateWordLiveData: LiveData<String> = _translateWordLiveData

    //kakao Api 번역.
    fun searchKakaoWord() {
        searchWordLiveData.value?.let { searchWord ->
            searchRepository.searchKakaoWord(searchWord) {
                _translateWordLiveData.value = it.translated_text[0][0]
            }
        }
    }

    fun searchNaverWord() {
        searchWordLiveData.value?.let { searchWord ->

            searchRepository.searchNaverWord(searchWord) {
                Log.d("결과", searchWord)
            }

        }
    }

}