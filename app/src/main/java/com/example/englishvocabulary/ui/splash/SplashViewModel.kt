package com.example.englishvocabulary.ui.splash

import android.app.Application
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SplashViewModel(
    app: Application,
) : BaseViewModel(app) {

    private val splashInteractor by inject(SplashInteractor::class.java)

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