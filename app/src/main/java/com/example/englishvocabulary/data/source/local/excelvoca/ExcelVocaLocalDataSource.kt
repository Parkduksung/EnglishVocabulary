package com.example.englishvocabulary.data.source.local.excelvoca

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

interface ExcelVocaLocalDataSource {

    fun getExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    fun verifyExcelData(
        callback: (isVerify: Boolean) -> Unit
    )

    fun getWantDayExcelData(
        day : String,
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    fun toggleBookmarkExcelData(
        toggleBookmark: Boolean,
        item : ExcelData,
        callback: (isSuccess: Boolean) -> Unit
    )

    fun getAllBookmarkExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )
    suspend fun checkExistExcelVoca(): Boolean
}