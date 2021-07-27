package com.example.englishvocabulary.ui.splash

import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject


class SplashInteractor {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaRepository.checkExistExcelVoca()
    }

    suspend fun registerExcelVocaData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaRepository.registerExcelVocaData()
    }
}
