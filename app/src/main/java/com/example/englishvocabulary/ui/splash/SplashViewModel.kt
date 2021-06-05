package com.example.englishvocabulary.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app),
    LifecycleObserver {


    @Inject
    lateinit var excelVocaRepository: ExcelVocaRepository

    @Inject
    lateinit var splashInteractor: SplashInteractor


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun verifyExcelVocaData() {
        viewModelScope.launch {
            if (splashInteractor.checkExistExcelVoca()) {
                withContext(Dispatchers.Main) {
                    viewStateChanged(SplashViewState.RouteMain)
                }
            } else {
                // 실패 메세지를 띄우던 exception 처리가 필요함.
            }
        }
    }

    sealed class SplashViewState : ViewState {
        object RouteMain : SplashViewState()
    }
}