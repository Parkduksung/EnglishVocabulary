package com.example.englishvocabulary.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.repository.SearchRepository
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SearchViewModel(
    app: Application
) :
    BaseViewModel(app) {

    private val searchRepository by inject(SearchRepository::class.java)

    val searchWordLiveData = MutableLiveData<String>()


    //kakao Api 번역.
    fun searchKakaoWord() {
        viewModelMainScope.launch {
            when (val searchResult = searchRepository.searchKakaoWord(searchWordLiveData.value)) {
                is Result.Success -> {
                    viewStateChanged(SearchViewstate.Translate(searchResult.value.translated_text[0][0]))
                }

                is Result.Failure -> {
                    //CustomError 관리 필요.
                    viewStateChanged(SearchViewstate.Error(searchResult.throwable.toString()))
                }
            }
        }
    }

    sealed class SearchViewstate : ViewState {
        data class Error(val errorMessage: String) : SearchViewstate()
        data class Translate(val translateWord: String) : SearchViewstate()
    }
}