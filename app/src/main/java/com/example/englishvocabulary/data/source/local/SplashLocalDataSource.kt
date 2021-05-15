package com.example.englishvocabulary.data.source.local

import com.example.englishvocabulary.data.model.ExcelData

interface SplashLocalDataSource {

    fun getExcelData(
        callback: (excelList: List<ExcelData>) -> Unit
    )
}