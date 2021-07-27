package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class ExcelVocaRepositoryImpl : ExcelVocaRepository {

    private val excelVocaLocalDataSource by inject(ExcelVocaLocalDataSource::class.java)

    override fun getExcelData(callback: (excelList: List<ExcelVocaEntity>) -> Unit) {
        excelVocaLocalDataSource.getExcelData(callback)
    }

    override fun getWantDayExcelData(
        day: String,
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    ) {
        excelVocaLocalDataSource.getWantDayExcelData(day, callback)
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
}