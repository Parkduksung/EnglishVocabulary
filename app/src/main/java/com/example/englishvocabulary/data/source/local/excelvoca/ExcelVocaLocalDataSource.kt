package com.example.englishvocabulary.data.source.local.excelvoca

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result

interface ExcelVocaLocalDataSource {

    suspend fun getAllExcelData(): Result<List<ExcelVocaEntity>>

    suspend fun toggleBookmarkExcelData(
        item: ExcelData
    ): Result<ExcelVocaEntity>

    suspend fun getAllBookmarkList(): Result<List<ExcelVocaEntity>>

    suspend fun checkExistExcelVoca(): Boolean

    suspend fun registerExcelVocaData(): Boolean

    suspend fun getWantDayExcelVocaData(
        wantDay: String
    ): Result<List<ExcelVocaEntity>>

}