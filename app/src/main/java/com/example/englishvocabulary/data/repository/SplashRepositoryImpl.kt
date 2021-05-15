package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.source.local.SplashLocalDataSource
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(private val splashLocalDataSource: SplashLocalDataSource) :
    SplashRepository {

    override fun getExcelData(callback: (excelList: List<ExcelData>) -> Unit) {
        splashLocalDataSource.getExcelData(callback)
    }
}