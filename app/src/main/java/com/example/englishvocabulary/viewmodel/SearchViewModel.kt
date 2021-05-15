package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.englishvocabulary.App
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    app: Application,
    private val searchRepository: SearchRepository
) :
    BaseViewModel(app) {

    val searchWordLiveData = MutableLiveData<String>()


    fun searchWord() {
        searchWordLiveData.value?.let { searchWord ->
            searchRepository.searchWord(searchWord) {
                Toast.makeText(App.instance.context(), it, Toast.LENGTH_LONG).show()
            }

        }
    }
}