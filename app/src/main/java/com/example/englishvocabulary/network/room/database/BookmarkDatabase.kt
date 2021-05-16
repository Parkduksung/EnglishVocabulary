package com.example.englishvocabulary.network.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.englishvocabulary.network.room.dao.BookmarkDao
import com.example.englishvocabulary.network.room.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao
}