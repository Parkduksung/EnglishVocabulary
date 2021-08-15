package com.example.englishvocabulary.ui.home.search

import android.app.Application
import androidx.lifecycle.LiveData
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

    private val _translateWordLiveData = MutableLiveData<String>()
    val translateWordLiveData: LiveData<String> = _translateWordLiveData

    //kakao Api 번역.
    fun searchKakaoWord() {

        viewModelMainScope.launch {

            searchWordLiveData.value?.let { searchWord ->
                when (val searchResult = searchRepository.searchKakaoWord(searchWord)) {
                    is Result.Success -> {
                        viewStateChanged(SearchViewstate.Translate(searchResult.value.translated_text[0][0]))
                    }

                    is Result.Failure -> {
                        viewStateChanged(SearchViewstate.Error(searchResult.throwable.message!!))
                    }
                }
            }
        }
    }

    sealed class SearchViewstate : ViewState {
        data class Error(val errorMessage: String) : SearchViewstate()
        data class Translate(val translateWord: String) : SearchViewstate()
    }
}