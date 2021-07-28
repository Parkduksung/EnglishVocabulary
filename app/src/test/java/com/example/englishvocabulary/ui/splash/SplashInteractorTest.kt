package com.example.englishvocabulary.ui.splash

import base.BaseTest
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito

class SplashInteractorTest : BaseTest() {

    @Mock
    lateinit var excelVocaRepository: ExcelVocaRepository

    private lateinit var splashInteractor: SplashInteractor

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { excelVocaRepository }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        splashInteractor = SplashInteractor()
    }

    // ExcelVocaData 가 로컬DB 에 저장되어 있는지 확인한다. (이미 있을때)
    @Test
    fun checkExistExcelVocaTest() = runBlocking {
        Mockito.`when`(excelVocaRepository.checkExistExcelVoca()).thenReturn(true)
        val isExistExcelVoca = splashInteractor.checkExistExcelVoca()
        MatcherAssert.assertThat("이미 데이터가 존재하지 않아서 실패", isExistExcelVoca, Matchers.`is`(true))
    }

    // ExcelVocaData 가 로컬DB 에 저장되어 있는지 확인한다. (없을때)
    @Test
    fun checkExistExcelVocaFailTest() = runBlocking {
        Mockito.`when`(excelVocaRepository.checkExistExcelVoca()).thenReturn(false)
        val isExistExcelVoca = splashInteractor.checkExistExcelVoca()
        MatcherAssert.assertThat("이미 데이터가 존재해서 실패", isExistExcelVoca, Matchers.`is`(false))
    }

    // ExcelVocaData 가 로컬DB 에 저장이 되는지 확인한다. (저장이 잘 됬을때)
    @Test
    fun checkRegisterExcelVocaDataTest() = runBlocking {
        Mockito.`when`(excelVocaRepository.registerExcelVocaData()).thenReturn(true)
        val isRegisterExcelVocaData = splashInteractor.registerExcelVocaData()
        MatcherAssert.assertThat("데이터가 저장이 되지 않아서 실패", isRegisterExcelVocaData, Matchers.`is`(true))
    }

    // ExcelVocaData 가 로컬DB 에 저장이 되는지 확인한다. (저장이 잘 안됬을때)
    @Test
    fun checkRegisterExcelVocaDataFailTest() = runBlocking {
        Mockito.`when`(excelVocaRepository.registerExcelVocaData()).thenReturn(false)
        val isRegisterExcelVocaData = splashInteractor.registerExcelVocaData()
        MatcherAssert.assertThat("데이터가 저장이 되지 않아서 실패", isRegisterExcelVocaData, Matchers.`is`(false))
    }


}
