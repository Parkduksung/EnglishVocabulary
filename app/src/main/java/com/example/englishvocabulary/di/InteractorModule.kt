package com.example.englishvocabulary.di

import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.ui.splash.SplashInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class InteractorModule {


    @Provides
    @Singleton
    fun provideSplashInteractor(excelVocaRepository: ExcelVocaRepository) =
        SplashInteractor(excelVocaRepository)

}