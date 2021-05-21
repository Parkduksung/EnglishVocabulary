package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.App
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.repository.SearchRepository
import com.example.englishvocabulary.ui.home.search.Papago
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    app: Application,
    private val searchRepository: SearchRepository
) :
    BaseViewModel(app) {

    val searchWordLiveData = MutableLiveData<String>()

    private val _translateWordLiveData = MutableLiveData<String>()
    val translateWordLiveData: LiveData<String> = _translateWordLiveData

    //kakao Api 번역.
    fun searchWord() {
        searchWordLiveData.value?.let { searchWord ->
            searchRepository.searchKakaoWord(searchWord) {
                _translateWordLiveData.value = it.translated_text[0][0]
            }
        }
    }

}