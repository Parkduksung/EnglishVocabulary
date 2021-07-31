package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result

interface ExcelVocaRepository {

    fun getExcelData(
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

    suspend fun getWantDayExcelVocaData(
        wantDay: String
    ): Result<List<ExcelVocaEntity>>

    suspend fun checkExistExcelVoca(): Boolean

    suspend fun registerExcelVocaData(): Boolean

}