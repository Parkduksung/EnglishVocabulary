package com.example.englishvocabulary.koin

import com.example.englishvocabulary.ui.splash.SplashInteractor
import org.koin.core.module.Module
import org.koin.dsl.module

class AppKoinSetup : KoinBaseSetup() {

    private val interactorModule = module {
        factory { SplashInteractor() }
    }


    override fun getModules(): List<Module> {
        return listOf(
            interactorModule
        )
    }

}