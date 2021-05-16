package com.example.englishvocabulary.network.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.englishvocabulary.data.model.ExcelData

@Entity(tableName = "excel_voca_table")
data class ExcelVocaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "mean") val mean: String,
    @ColumnInfo(name = "like") val like: Boolean,
) {

    fun toExcelData(): ExcelData =
        ExcelData(
            day = day,
            word = word,
            mean = mean,
            like = like
        )

}