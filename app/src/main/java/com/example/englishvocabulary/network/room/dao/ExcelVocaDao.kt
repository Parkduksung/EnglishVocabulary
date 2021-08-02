package com.example.englishvocabulary.network.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result

@Dao
interface ExcelVocaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerExcelVocaEntity(excelList: ExcelVocaEntity): Long

    @Query("SELECT * FROM excel_voca_table")
    fun getAll(): List<ExcelVocaEntity>


    @Query("SELECT * FROM excel_voca_table WHERE day = (:wantDay)")
    fun getDayExcelVocaEntity(wantDay: String): List<ExcelVocaEntity>

    @Query("UPDATE excel_voca_table SET `like` = (:like) WHERE  day = (:day) AND word = (:word) AND mean = (:mean)")
    fun updateBookmarkExcelData(
        day: String,
        word: String,
        mean: String,
        like: Boolean
    ): Int

    @Query("SELECT * FROM excel_voca_table WHERE `like` = (:like)")
    fun getBookmarkExcelVocaEntity(like: Boolean = true): List<ExcelVocaEntity>

}