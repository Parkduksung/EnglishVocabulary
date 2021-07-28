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


    // 시작하였을때 애니메이션이 잘 되는지 확인한다.
    @Test
    fun startTest() = runBlocking {
        splashViewModel.startSplash()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.SplashAnimation)
    }

    // ExcelVocaData 가 존재하는지 확인한다.
    @Test
    fun checkExistExcelVocaTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(true)
        splashViewModel.verifyExcelVocaData()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.RouteMain)
    }

    // ExcelVocaData 가 존재하지 않았을때 ExcelVocaData 잘 저장되는지 확인한다.
    @Test
    fun checkRegisterExcelVocaDataTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(false)
        Mockito.`when`(splashInteractor.registerExcelVocaData()).thenReturn(true)
        splashViewModel.verifyExcelVocaData()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.RouteMain)
    }

    // ExcelVocaData 저장되지 않았을때, 에러가 뜨지는 확인한다.
    @Test
    fun checkErrorTest() = runBlocking {
        Mockito.`when`(splashInteractor.checkExistExcelVoca()).thenReturn(false)
        Mockito.`when`(splashInteractor.registerExcelVocaData()).thenReturn(false)
        splashViewModel.verifyExcelVocaData()
        Mockito.verify(viewStateObserver).onChanged(SplashViewModel.SplashViewState.Error)
    }


}