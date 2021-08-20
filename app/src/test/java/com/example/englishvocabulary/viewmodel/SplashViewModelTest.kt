package com.example.englishvocabulary.viewmodel

import base.ViewModelBaseTest
import com.example.englishvocabulary.interactor.SplashInteractor
import com.example.englishvocabulary.viewmodel.SplashViewModel
import com.example.englishvocabulary.viewmodel.SplashViewModel.Companion.SPLASH_DELAY_MILLIS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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


    // ExcelVocaData 가 존재하는지 확인한다.
    @Test
    fun checkExistExcelVocaTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(true)
        splashViewModel.verifyExcelVocaData()
        delay(SPLASH_DELAY_MILLIS)
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.RouteMain)
    }

    // ExcelVocaData 가 존재하지 않았을때 ExcelVocaData 잘 저장되는지 확인한다.
    @Test
    fun checkRegisterExcelVocaDataTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(false)
        Mockito.`when`(splashInteractor.registerExcelVocaData()).thenReturn(true)
        splashViewModel.verifyExcelVocaData()
        delay(SPLASH_DELAY_MILLIS)
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.RouteMain)
    }

    // ExcelVocaData 저장되지 않았을때, 에러가 뜨지는 확인한다.
    @Test
    fun checkErrorTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(false)
        Mockito.`when`(splashInteractor.registerExcelVocaData()).thenReturn(false)
        splashViewModel.verifyExcelVocaData()
        delay(SPLASH_DELAY_MILLIS)
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.Error)
    }


}