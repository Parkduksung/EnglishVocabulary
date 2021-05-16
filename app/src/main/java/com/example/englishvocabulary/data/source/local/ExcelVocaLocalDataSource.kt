package com.example.englishvocabulary.data.source.local

import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

interface ExcelVocaLocalDataSource {

    fun getExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    fun verifyExcelData(
        callback: (isVerify: Boolean) -> Unit
    )

}