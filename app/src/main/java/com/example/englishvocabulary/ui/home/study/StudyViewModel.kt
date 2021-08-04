package com.example.englishvocabulary.ui.home.study

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.base.ViewState
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class StudyViewModel(
    app: Application
) : BaseViewModel(app), LifecycleObserver {

    private val studyInteractor by inject(StudyInteractor::class.java)

    // 날짜에 따른 ExcelVoca 얻어오기.
    fun getAllExcelVoca(day: String) {
        viewModelMainScope.launch {
            when (val result = studyInteractor.getWantExcelVocaData(wantDay = day.toLowerCase())) {
                is Result.Success -> {
                    viewStateChanged(StudyViewState.ExcelVoca(result.value.map { it.toExcelData() }))
                }
                is Result.Failure -> {
                    viewStateChanged(StudyViewState.Error(result.throwable.message!!))
                }
            }
        }
    }


    sealed class StudyViewState : ViewState {
        data class Error(val errorMessage: String) : StudyViewState()
        data class ExcelVoca(val wandData: List<ExcelData>) : StudyViewState()
    }
}