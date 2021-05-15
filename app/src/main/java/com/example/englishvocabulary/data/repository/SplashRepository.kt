package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData

interface SplashRepository {

    fun getExcelData(
        callback: (excelList: List<ExcelData>) -> Unit
    )

}