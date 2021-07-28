package com.example.englishvocabulary.ui.splash

import base.ViewModelBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
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


    @Test
    fun start() = runBlocking {
        splashViewModel.startSplash()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.SplashAnimation)
    }

    @Test
    fun checkExistExcelVocaTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(true)
        splashViewModel.verifyExcelVocaData()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.RouteMain)
    }

    @Test
    fun checkExistExcelVocaTest1() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(false)
        Mockito.`when`(splashInteractor.registerExcelVocaData()).thenReturn(true)
        splashViewModel.verifyExcelVocaData()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.RouteMain)
    }


    @Test
    fun checkExistExcelVocaTest2() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(false)
        Mockito.`when`(splashInteractor.registerExcelVocaData()).thenReturn(false)
        splashViewModel.verifyExcelVocaData()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.Error)
    }


}