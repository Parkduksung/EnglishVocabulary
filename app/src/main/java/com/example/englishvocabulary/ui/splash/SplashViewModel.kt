package com.example.englishvocabulary.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    app: Application,
    private val excelVocaRepository: ExcelVocaRepository
) : BaseViewModel(app),
    LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun verifyExcelVocaData() {
        excelVocaRepository.getExcelData {
            if (it.isEmpty()) {
                verifyExcelData()
            } else {
                viewStateChanged(SplashViewState.RouteMain)
            }
        }
    }

    private fun verifyExcelData() {
        excelVocaRepository.verifyExcelData { isVerify: Boolean ->
            if (isVerify) {
                viewStateChanged(SplashViewState.RouteMain)
            }
        }
    }


    sealed class SplashViewState : ViewState {
        object RouteMain : SplashViewState()
    }
}