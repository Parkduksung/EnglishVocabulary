package com.example.englishvocabulary

import android.app.Application
import android.content.Context
import com.example.englishvocabulary.di.networkModule
import com.example.englishvocabulary.di.repositoryModule
import com.example.englishvocabulary.di.sourceModule
import com.example.englishvocabulary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKOIN()
    }

    private fun startKOIN() {
        startKoin {
            androidContext(this@App)
            listOf(
                networkModule,
                repositoryModule,
                viewModelModule,
                sourceModule
            )
        }
    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: App
            private set
    }
}