package com.example.englishvocabulary.data.repository

import base.BaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito


class ExcelVocaRepositoryImplTest : BaseTest() {

    @Mock
    lateinit var excelVocaLocalDataSource: ExcelVocaLocalDataSource

    private lateinit var excelVocaRepositoryImpl: ExcelVocaRepository

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { excelVocaLocalDataSource }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        excelVocaRepositoryImpl = ExcelVocaRepositoryImpl()
    }

    @Test
    fun checkExistExcelVocaSuccessTest() = runBlocking {
        Mockito.`when`(excelVocaLocalDataSource.checkExistExcelVoca()).thenReturn(true)

        MatcherAssert.assertThat(excelVocaRepositoryImpl.checkExistExcelVoca(), Matchers.`is`(true))
    }

    @Test
    fun checkExistExcelVocaFailTest() = runBlocking {
        Mockito.`when`(excelVocaLocalDataSource.checkExistExcelVoca()).thenReturn(false)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.checkExistExcelVoca(),
            Matchers.`is`(false)
        )
    }

    @Test
    fun checkRegisterExcelVocaDataSuccessTest() = runBlocking {
        Mockito.`when`(excelVocaLocalDataSource.registerExcelVocaData()).thenReturn(true)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.registerExcelVocaData(),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkRegisterExcelVocaDataFailTest() = runBlocking {
        Mockito.`when`(excelVocaLocalDataSource.registerExcelVocaData()).thenReturn(false)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.registerExcelVocaData(),
            Matchers.`is`(false)
        )
    }


    @Test
    fun checkGetAllBookmarkListSuccessTest() = runBlocking {

        val mockExcelVocaEntityList =
            listOf(ExcelData("day1", "resume", "이력서", false).toExcelVocaEntity())

        val successResult = Result.success(mockExcelVocaEntityList)

        Mockito.`when`(excelVocaLocalDataSource.getAllBookmarkList()).thenReturn(successResult)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.getAllBookmarkList(),
            Matchers.`is`(successResult)
        )
    }


    @Test
    fun checkToggleBookmarkExcelDataSuccessTest() = runBlocking {

        val mockExcelData =
            ExcelData("day1", "resume", "이력서", false)

        val successResult =
            Result.success(mockExcelData.toExcelVocaEntity().copy(like = !mockExcelData.like))

        Mockito.`when`(excelVocaLocalDataSource.toggleBookmarkExcelData(mockExcelData))
            .thenReturn(successResult)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.toggleBookmarkExcelData(mockExcelData),
            Matchers.`is`(successResult)
        )
    }

    @Test
    fun checkGetAllBookmarkListFailTest() = runBlocking {

        val failureResult =
            Result.failure<List<ExcelVocaEntity>>(Throwable("bookmarkList is Null!"))

        Mockito.`when`(excelVocaLocalDataSource.getAllBookmarkList()).thenReturn(failureResult)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.getAllBookmarkList(),
            Matchers.`is`(failureResult)
        )
    }

    @Test
    fun checkToggleBookmarkExcelDataFailTest() = runBlocking {

        val mockExcelData =
            ExcelData("day1", "resume", "이력서", false)

        val failureResult = Result.failure<ExcelVocaEntity>(Throwable())

        Mockito.`when`(excelVocaLocalDataSource.toggleBookmarkExcelData(mockExcelData))
            .thenReturn(failureResult)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.toggleBookmarkExcelData(mockExcelData),
            Matchers.`is`(failureResult)
        )
    }

    @Test
    fun checkGetWantDayExcelVocaDataSuccessTest() = runBlocking {

        val mockExcelVocaEntityList =
            listOf(ExcelData("day1", "resume", "이력서", false).toExcelVocaEntity())


        val successResult = Result.success(mockExcelVocaEntityList)

        Mockito.`when`(excelVocaLocalDataSource.getWantDayExcelVocaData("day1"))
            .thenReturn(successResult)


        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.getWantDayExcelVocaData("day1"),
            Matchers.`is`(successResult)
        )
    }

    @Test
    fun checkGetWantDayExcelVocaDataFailTest() = runBlocking {

        val throwable =
            Throwable("Specific Excel Voca is Empty Or Null")


        val failureResult = Result.failure<List<ExcelVocaEntity>>(throwable)

        Mockito.`when`(excelVocaLocalDataSource.getWantDayExcelVocaData("day1"))
            .thenReturn(failureResult)


        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.getWantDayExcelVocaData("day1"),
            Matchers.`is`(failureResult)
        )
    }

    @Test
    fun checkGetAllExcelDataSuccessTest() = runBlocking {
        val mockExcelVocaEntityList =
            listOf(ExcelData("day1", "resume", "이력서", false).toExcelVocaEntity())

        val successResult = Result.success(mockExcelVocaEntityList)

        Mockito.`when`(excelVocaLocalDataSource.getAllExcelData())
            .thenReturn(successResult)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.getAllExcelData(),
            Matchers.`is`(successResult)
        )
    }

    @Test
    fun checkGetAllExcelDataFailTest() = runBlocking {
        val throwable =
            Throwable()

        val failureResult = Result.failure<List<ExcelVocaEntity>>(throwable)

        Mockito.`when`(excelVocaLocalDataSource.getAllExcelData())
            .thenReturn(failureResult)

        MatcherAssert.assertThat(
            excelVocaRepositoryImpl.getAllExcelData(),
            Matchers.`is`(failureResult)
        )
    }

}