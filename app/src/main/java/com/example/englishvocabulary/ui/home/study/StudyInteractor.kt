package com.example.englishvocabulary.ui.home.study

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class StudyInteractor {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    suspend fun getWantExcelVocaData(wantDay: String): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext excelVocaRepository.getWantDayExcelVocaData(wantDay)
        }

    suspend fun toggleBookmarkExcelData(item: ExcelData): Result<ExcelVocaEntity> =
        withContext(Dispatchers.IO) {
            return@withContext excelVocaRepository.toggleBookmarkExcelData(item)
        }

}