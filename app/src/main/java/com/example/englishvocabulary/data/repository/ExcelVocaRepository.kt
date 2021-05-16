package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

interface ExcelVocaRepository {

    fun getExcelData(
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    )

    fun verifyExcelData(
        callback: (isVerify: Boolean) -> Unit
    )

}