package com.example.englishvocabulary.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    @Inject
    lateinit var splashInteractor: SplashInteractor

    init {
        verifyExcelVocaData()
    }

    private fun verifyExcelVocaData() {
        viewModelScope.launch {
            if (splashInteractor.checkExistExcelVoca()) {
                startSplashAndRoute(SPLASH_DELAY_MILLIS)
            } else {
                // 실패 메세지를 띄우던 exception 처리가 필요함.
            }
        }
    }


    private fun startSplashAndRoute(delayTime: Long) {
        viewModelScope.launch {
            viewStateChanged(SplashViewState.SplashAnimation)
            delay(delayTime)
            viewStateChanged(SplashViewState.RouteMain)
        }
    }


    sealed class SplashViewState : ViewState {
        object SplashAnimation : SplashViewState()
        object RouteMain : SplashViewState()
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 1500L
    }
}