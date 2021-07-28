package com.example.englishvocabulary.ui.splash

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SplashViewModel(
    app: Application,
) : BaseViewModel(app), LifecycleObserver {

    private val splashInteractor by inject(SplashInteractor::class.java)


    fun verifyExcelVocaData() {
        viewModelScope.launch {
            if (splashInteractor.checkExistExcelVoca()) {
                viewStateChanged(SplashViewState.RouteMain)
            } else {
                if (splashInteractor.registerExcelVocaData()) {
                    viewStateChanged(SplashViewState.RouteMain)
                } else {
                    viewStateChanged(SplashViewState.Error)
//                    TODO("ErrorType 들에 대한 대응처리 할 것.")
                }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startSplash() {
        viewModelScope.launch {
            viewStateChanged(SplashViewState.SplashAnimation)
            delay(SPLASH_DELAY_MILLIS)
            verifyExcelVocaData()
        }
    }


    sealed class SplashViewState : ViewState {
        object SplashAnimation : SplashViewState()
        object RouteMain : SplashViewState()
        object Error : SplashViewState()
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 1000L
    }

}