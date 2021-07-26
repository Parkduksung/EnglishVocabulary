package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

interface ExcelVocaRepository {

    fun getExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    fun getWantDayExcelData(
        day: String,
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    fun toggleBookmarkExcelData(
        toggleBookmark: Boolean,
        item: ExcelData,
        callback: (isSuccess: Boolean) -> Unit
    )

    fun getAllBookmarkExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    suspend fun checkExistExcelVoca(): Boolean

    suspend fun registerExcelVocaData(): Boolean

}