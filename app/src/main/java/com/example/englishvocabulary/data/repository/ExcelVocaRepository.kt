package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result

interface ExcelVocaRepository {

    fun getExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    suspend fun toggleBookmarkExcelData(
        item: ExcelData
    ): Result<ExcelVocaEntity>

    suspend fun getAllBookmarkList() : Result<List<ExcelVocaEntity>>

    suspend fun getWantDayExcelVocaData(
        wantDay: String
    ): Result<List<ExcelVocaEntity>>

    suspend fun checkExistExcelVoca(): Boolean

    suspend fun registerExcelVocaData(): Boolean

}