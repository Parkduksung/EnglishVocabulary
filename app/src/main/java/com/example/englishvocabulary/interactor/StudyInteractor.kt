package com.example.englishvocabulary.interactor

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class StudyInteractor {

    private val excelVocaRepository by inject(ExcelVocaRepository::class.java)

    suspend fun getWantExcelVocaData(wantDay: String?): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext if (!checkValidDay(wantDay)) {
                Result.failure(Throwable("dayValue is Null or Empty!"))
            } else {
                excelVocaRepository.getWantDayExcelVocaData(wantDay!!.toLowerCase())
            }
        }

    suspend fun toggleBookmarkExcelData(item: ExcelData): Result<ExcelVocaEntity> =
        withContext(Dispatchers.IO) {
            return@withContext excelVocaRepository.toggleBookmarkExcelData(item)
        }


    private fun checkValidDay(day: String?): Boolean =
        dayList.contains(day)


    companion object {
        private val dayList = mutableListOf<String>().apply {
            for (i in 1..30) {
                add("Day${i}")
            }
        }
    }

}