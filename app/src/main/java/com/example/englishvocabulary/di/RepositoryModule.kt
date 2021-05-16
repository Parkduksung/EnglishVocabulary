package com.example.englishvocabulary.di

import com.example.englishvocabulary.data.repository.SearchRepository
import com.example.englishvocabulary.data.repository.SearchRepositoryImpl
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.data.repository.ExcelVocaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Singleton
    @Binds
    abstract fun bindExcelVocaRepository(excelVocaRepositoryImpl: ExcelVocaRepositoryImpl): ExcelVocaRepository
}

