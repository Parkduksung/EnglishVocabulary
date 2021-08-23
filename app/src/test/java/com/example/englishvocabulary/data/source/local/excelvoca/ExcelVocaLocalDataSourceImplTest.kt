package com.example.englishvocabulary.data.source.local.excelvoca

import base.BaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.dao.ExcelVocaDao
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
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

class ExcelVocaLocalDataSourceImplTest : BaseTest() {

    @Mock
    lateinit var excelVocaDatabase: ExcelVocaDatabase

    @Mock
    lateinit var excelVocaDao: ExcelVocaDao

    private lateinit var excelVocaLocalDataSourceImpl: ExcelVocaLocalDataSource

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { excelVocaDatabase }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        excelVocaLocalDataSourceImpl = ExcelVocaLocalDataSourceImpl()
        excelVocaDao = Mockito.mock(ExcelVocaDao::class.java)
    }

    private fun mockExcelVocaList(excelData: ExcelData) =
        listOf(excelData.toExcelVocaEntity())

    @Test
    fun checkGetAllExcelDataSuccessTest() = runBlocking {

        val mockExcelVocaEntityList =
            mockExcelVocaList(ExcelData("day1", "resume", "이력서", false))

        val successResult = Result.success(mockExcelVocaEntityList)

        Mockito.`when`(excelVocaDao.getAll()).thenReturn((successResult as Result.Success).value)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.getAllExcelData() as Result.Success).value, Matchers.`is`(
                successResult.value
            )
        )
    }

    @Test
    fun checkGetAllExcelDataFailTest() = runBlocking {

        val throwable =
            Throwable("Error GetAllExcelEntity")

        val failureResult = Result.failure<List<ExcelVocaEntity>>(throwable)

        Mockito.`when`(excelVocaDao.getAll()).then { throwable }
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.getAllExcelData() as Result.Failure).throwable.message,
            Matchers.`is`(
                (failureResult as Result.Failure).throwable.message
            )
        )
    }


    @Test
    fun checkExistExcelVocaSuccessTest() = runBlocking {

        val mockExcelVocaEntityList =
            mockExcelVocaList(ExcelData("day1", "resume", "이력서", false))

        Mockito.`when`(excelVocaDao.getAll()).thenReturn(mockExcelVocaEntityList)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            excelVocaLocalDataSourceImpl.checkExistExcelVoca(),
            Matchers.`is`(true)
        )
    }

    @Test
    fun checkExistExcelVocaFailTest() = runBlocking {

        val mockExcelVocaEntityList =
            emptyList<ExcelVocaEntity>()

        Mockito.`when`(excelVocaDao.getAll()).thenReturn(mockExcelVocaEntityList)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            excelVocaLocalDataSourceImpl.checkExistExcelVoca(),
            Matchers.`is`(false)
        )
    }

    @Test
    fun checkGetAllBookmarkListSuccessTest() = runBlocking {

        val mockExcelVocaEntityList =
            mockExcelVocaList(ExcelData("day1", "resume", "이력서", true))

        val successResult = Result.success(mockExcelVocaEntityList)

        Mockito.`when`(excelVocaDao.getBookmarkExcelVocaEntity(like = true))
            .thenReturn((successResult as Result.Success).value)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.getAllBookmarkList() as Result.Success).value,
            Matchers.`is`(
                successResult.value
            )
        )
    }

    @Test
    fun checkGetAllBookmarkListFailTest() = runBlocking {

        val throwable =
            Throwable("bookmarkList is Null!")

        val failureResult = Result.failure<List<ExcelVocaEntity>>(throwable)

        Mockito.`when`(excelVocaDao.getBookmarkExcelVocaEntity(like = true)).then { throwable }
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.getAllBookmarkList() as Result.Failure).throwable.message,
            Matchers.`is`(
                (failureResult as Result.Failure).throwable.message
            )
        )
    }

    @Test
    fun checkGetDayExcelVocaEntitySuccessTest() = runBlocking {

        val mockExcelVocaEntity =
            mockExcelVocaList(ExcelData("day1", "resume", "이력서", false))

        Mockito.`when`(excelVocaDao.getDayExcelVocaEntity("day1")).thenReturn(mockExcelVocaEntity)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)


        MatcherAssert.assertThat(
            excelVocaDatabase.excelVocaDao().getDayExcelVocaEntity("day1").isNullOrEmpty(),
            Matchers.`is`(false)
        )

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.getWantDayExcelVocaData("day1") as Result.Success).value,
            Matchers.`is`(
                mockExcelVocaEntity
            )
        )
    }

    @Test
    fun checkGetDayExcelVocaEntityFailTest() = runBlocking {

        val throwable =
            Throwable("Specific Excel Voca is Empty Or Null")


        val mockExcelVocaEntity =
            emptyList<ExcelVocaEntity>()

        Mockito.`when`(excelVocaDao.getDayExcelVocaEntity("day1")).thenReturn(mockExcelVocaEntity)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)


        MatcherAssert.assertThat(
            excelVocaDatabase.excelVocaDao().getDayExcelVocaEntity("day1").isNullOrEmpty(),
            Matchers.`is`(true)
        )

        val failureResult = Result.failure<List<ExcelVocaEntity>>(throwable)

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.getWantDayExcelVocaData("day1") as Result.Failure).throwable.message,
            Matchers.`is`(
                (failureResult as Result.Failure).throwable.message
            )
        )
    }

    @Test
    fun checkUpdateBookmarkExcelDataSuccessTest() = runBlocking {

        val mockExcelVocaEntity =
            ExcelData("day1", "resume", "이력서", false)

        val successResult = Result.success(mockExcelVocaEntity.copy(like = true))

        Mockito.`when`(excelVocaDao.updateBookmarkExcelData("day1", "resume", "이력서", true))
            .thenReturn(1)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)

        MatcherAssert.assertThat(
            excelVocaDatabase.excelVocaDao()
                .updateBookmarkExcelData("day1", "resume", "이력서", true),
            Matchers.`is`(
                1
            )
        )

        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.toggleBookmarkExcelData(mockExcelVocaEntity) as Result.Success).value,
            Matchers.`is`(
                (successResult as Result.Success).value.toExcelVocaEntity()
            )
        )
    }

    @Test
    fun checkUpdateBookmarkExcelDataFailTest() = runBlocking {

        val mockExcelVocaEntity =
            ExcelData("day1", "resume", "이력서", false)

        val failureResult = Result.failure<List<ExcelVocaEntity>>(Throwable("Error ToggleBookmark"))

        Mockito.`when`(excelVocaDao.updateBookmarkExcelData("day1", "resume", "이력서", true))
            .thenReturn(-1)
        Mockito.`when`(excelVocaDatabase.excelVocaDao()).thenReturn(excelVocaDao)


        MatcherAssert.assertThat(
            (excelVocaLocalDataSourceImpl.toggleBookmarkExcelData(mockExcelVocaEntity) as Result.Failure).throwable.message,
            Matchers.`is`(
                (failureResult as Result.Failure).throwable.message
            )
        )
    }

}
