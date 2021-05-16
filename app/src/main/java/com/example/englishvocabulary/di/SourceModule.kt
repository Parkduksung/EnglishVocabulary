package com.example.englishvocabulary.di

import com.example.englishvocabulary.data.source.local.bookmark.BookmarkLocalDataSource
import com.example.englishvocabulary.data.source.local.bookmark.BookmarkLocalDataSourceImpl
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSourceImpl
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindSearchRemoteDataSource(searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl): SearchRemoteDataSource


    @Singleton
    @Binds
    abstract fun bindExcelVocaLocalDataSource(excelVocaLocalDataSourceImpl: ExcelVocaLocalDataSourceImpl): ExcelVocaLocalDataSource


    @Singleton
    @Binds
    abstract fun bindBookmarkDataSource(bookmarkLocalDataSourceImpl: BookmarkLocalDataSourceImpl): BookmarkLocalDataSource

}