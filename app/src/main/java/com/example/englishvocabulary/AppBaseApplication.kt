package com.example.englishvocabulary

import android.app.Application
import com.example.englishvocabulary.koin.AppKoinSetup
import com.example.englishvocabulary.koin.KoinBaseSetup

class AppBaseApplication {

    private val koinSetup: KoinBaseSetup = AppKoinSetup()

    fun onCreate(application: Application) {
        koinSetup.setup(application)
    }
}