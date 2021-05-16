package com.example.englishvocabulary.data.model

import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

data class ExcelData(
    var day: String = "",
    var word: String = "",
    var mean: String = "",
    var like: Boolean = false
) {
    fun toExcelVocaEntity(): ExcelVocaEntity =
        ExcelVocaEntity(
            0,
            day, word, mean, like
        )
}