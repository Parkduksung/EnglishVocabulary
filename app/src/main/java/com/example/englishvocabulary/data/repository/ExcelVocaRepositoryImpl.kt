package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import javax.inject.Inject

class ExcelVocaRepositoryImpl @Inject constructor(private val excelVocaLocalDataSource: ExcelVocaLocalDataSource) :
    ExcelVocaRepository {

    override fun getExcelData(callback: (excelList: List<ExcelVocaEntity>) -> Unit) {
        excelVocaLocalDataSource.getExcelData(callback)
    }

    override fun verifyExcelData(callback: (isVerify: Boolean) -> Unit) {
        excelVocaLocalDataSource.verifyExcelData(callback)
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
}