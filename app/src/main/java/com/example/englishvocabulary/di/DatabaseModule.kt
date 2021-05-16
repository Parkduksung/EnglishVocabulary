package com.example.englishvocabulary.di

import android.content.Context
import androidx.room.Room
import com.example.englishvocabulary.network.room.dao.BookmarkDao
import com.example.englishvocabulary.network.room.dao.ExcelVocaDao
import com.example.englishvocabulary.network.room.database.BookmarkDatabase
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideExcelVocaDao(excelVocaDatabase: ExcelVocaDatabase): ExcelVocaDao {
        return excelVocaDatabase.excelVocaDao()
    }

    @Provides
    fun provideBookmarkDao(bookmarkDatabase: BookmarkDatabase): BookmarkDao {
        return bookmarkDatabase.bookmarkDao()
    }


    @Provides
    @Singleton
    fun provideExcelVocaDatabase(@ApplicationContext appContext: Context): ExcelVocaDatabase {
        return Room.databaseBuilder(
            appContext,
            ExcelVocaDatabase::class.java,
            "excel_voca_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookmarkDatabase(@ApplicationContext appContext: Context): BookmarkDatabase {
        return Room.databaseBuilder(
            appContext,
            BookmarkDatabase::class.java,
            "bookmark_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}