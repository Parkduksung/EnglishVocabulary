package com.example.englishvocabulary.ui.splash

import base.ViewModelBaseTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var splashInteractor: SplashInteractor

    private lateinit var splashViewModel: SplashViewModel

    override fun createModules(): List<Module> {
        return listOf(
            module {
                factory { splashInteractor }
            }
        )
    }

    override fun setup() {
        super.setup()
        splashViewModel = SplashViewModel(application)
        splashViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }

}