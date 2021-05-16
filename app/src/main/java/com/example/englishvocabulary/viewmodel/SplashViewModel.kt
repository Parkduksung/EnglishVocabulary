package com.example.englishvocabulary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.englishvocabulary.base.BaseViewModel
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
                excelVocaRepository.verifyExcelData {
                    Log.d("결과", it.toString())
                }
            } else {
                Log.d("결과", it.size.toString())
            }

        }
    }
}