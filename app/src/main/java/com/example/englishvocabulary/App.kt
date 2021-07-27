package com.example.englishvocabulary

import android.app.Application
import android.content.Context

class App : Application() {

    private val appBaseApplication = AppBaseApplication()

    override fun onCreate() {
        super.onCreate()
        appBaseApplication.onCreate(this)
        instance = this
    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: App
            private set
    }
}