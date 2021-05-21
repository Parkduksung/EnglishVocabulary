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


    // 로딩 하는 시간에 만약 RoomDB 에 값이 저장되어 있지 않으면 모두 저장하고 이미 저장이 되어있으면 그냥 넘어가게끔
    // 로딩하는 시간을 의미있게 사용한다고 보시면 됩니다.
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun verifyExcelVocaData() {
        excelVocaRepository.getExcelData {
            if (it.isEmpty()) {
                excelVocaRepository.verifyExcelData {
                }
            }
        }
    }
}