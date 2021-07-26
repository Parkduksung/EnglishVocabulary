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

            viewStateChanged(SplashViewState.SplashAnimation)

            if (splashInteractor.checkExistExcelVoca()) {
                startRoute(SPLASH_DELAY_MILLIS)
            } else {
                if (splashInteractor.registerExcelVocaData()) {
                    startRoute(SPLASH_DELAY_MILLIS)
                } else {
                    viewStateChanged(SplashViewState.Error)
                    TODO("ErrorType 들에 대한 대응처리 할 것.")
                }
            }
        }
    }


    private fun startRoute(delayTime: Long) {
        viewModelScope.launch {
            delay(delayTime)
            viewStateChanged(SplashViewState.RouteMain)
        }
    }


    sealed class SplashViewState : ViewState {
        object SplashAnimation : SplashViewState()
        object RouteMain : SplashViewState()
        object Error : SplashViewState()
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 1500L
    }

}