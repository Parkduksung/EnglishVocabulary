package com.example.englishvocabulary.network.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

@Dao
interface ExcelVocaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerExcelVocaEntity(excelList: ExcelVocaEntity): Long

    @Query("SELECT * FROM excel_voca_table")
    fun getAll(): List<ExcelVocaEntity>


    @Query("SELECT * FROM excel_voca_table WHERE day = (:wantDay)")
    fun getDayExcelVocaEntity(wantDay: String): List<ExcelVocaEntity>

}