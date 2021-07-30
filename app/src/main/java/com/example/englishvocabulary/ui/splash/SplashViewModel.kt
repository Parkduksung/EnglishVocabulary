package com.example.englishvocabulary.ui.splash

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SplashViewModel(
    app: Application,
) : BaseViewModel(app), LifecycleObserver {

    private val splashInteractor by inject(SplashInteractor::class.java)


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun verifyExcelVocaData() {
        viewModelIOScope.launch {
            delay(SPLASH_DELAY_MILLIS)
            if (splashInteractor.checkExistExcelVoca()) {
                viewModelMainScope.launch {
                    viewStateChanged(SplashViewState.RouteMain)
                }
            } else {
                if (splashInteractor.registerExcelVocaData()) {
                    viewModelMainScope.launch {
                        viewStateChanged(SplashViewState.RouteMain)
                    }
                } else {
                    viewModelMainScope.launch {
                        viewStateChanged(SplashViewState.Error)
                    }
//                    TODO("ErrorType 들에 대한 대응처리 할 것.")
                }
            }
        }
    }


    sealed class SplashViewState : ViewState {
        object RouteMain : SplashViewState()
        object Error : SplashViewState()
    }

    companion object {
        const val SPLASH_DELAY_MILLIS = 1000L
    }

}