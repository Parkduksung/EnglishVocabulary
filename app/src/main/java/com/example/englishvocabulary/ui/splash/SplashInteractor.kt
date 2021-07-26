package com.example.englishvocabulary.ui.splash

import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashInteractor @Inject constructor(private val excelVocaRepository: ExcelVocaRepository) {

    suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaRepository.checkExistExcelVoca()
    }

    suspend fun registerExcelVocaData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaRepository.registerExcelVocaData()
    }
}
