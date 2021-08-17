package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class ExcelVocaRepositoryImpl : ExcelVocaRepository {

    private val excelVocaLocalDataSource by inject(ExcelVocaLocalDataSource::class.java)

    override suspend fun getAllExcelData(): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext excelVocaLocalDataSource.getAllExcelData()
        }

    override suspend fun toggleBookmarkExcelData(
        item: ExcelData
    ): Result<ExcelVocaEntity> = withContext(Dispatchers.IO) {
        return@withContext excelVocaLocalDataSource.toggleBookmarkExcelData(item)
    }

    override suspend fun getAllBookmarkList(): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext excelVocaLocalDataSource.getAllBookmarkList()
        }

    override suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaLocalDataSource.checkExistExcelVoca()
    }

    override suspend fun registerExcelVocaData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaLocalDataSource.registerExcelVocaData()
    }

    override suspend fun getWantDayExcelVocaData(wantDay: String): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext excelVocaLocalDataSource.getWantDayExcelVocaData(wantDay)
        }
}