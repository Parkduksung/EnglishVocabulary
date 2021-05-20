package com.example.englishvocabulary.viewmodel

import android.app.Application
import com.example.englishvocabulary.base.BaseViewModel
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    app: Application,
    private val excelVocaRepository: ExcelVocaRepository
) : BaseViewModel(app) {





}