package com.example.englishvocabulary.network.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.englishvocabulary.network.room.dao.ExcelVocaDao
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity

@Database(entities = [ExcelVocaEntity::class], version = 1)
abstract class ExcelVocaDatabase : RoomDatabase() {

    abstract fun excelVocaDao(): ExcelVocaDao
}