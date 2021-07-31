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

    override fun getExcelData(callback: (excelList: List<ExcelVocaEntity>) -> Unit) {
        excelVocaLocalDataSource.getExcelData(callback)
    }


    override fun toggleBookmarkExcelData(
        toggleBookmark: Boolean,
        item: ExcelData,
        callback: (isSuccess: Boolean) -> Unit
    ) {
        excelVocaLocalDataSource.toggleBookmarkExcelData(toggleBookmark, item, callback)
    }

    override fun getAllBookmarkExcelData(callback: (excelList: List<ExcelVocaEntity>) -> Unit) {
        excelVocaLocalDataSource.getAllBookmarkExcelData(callback)
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