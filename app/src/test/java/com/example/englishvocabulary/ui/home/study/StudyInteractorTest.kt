package com.example.englishvocabulary.ui.home.study

import base.BaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.util.Result
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito

class StudyInteractorTest : BaseTest() {


    @Mock
    lateinit var excelVocaRepository: ExcelVocaRepository

    private lateinit var studyInteractor: StudyInteractor

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { excelVocaRepository }
            }
        )
    }

    companion object {

        private val mockExcelVocaEntityList =
            listOf(ExcelData(day = "Day1", word = "resume", mean = "이력서").toExcelVocaEntity())
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        studyInteractor = StudyInteractor()
    }

    // 날짜에 대한 ExcelVocaList 가 제대로 나오는지 확인.
    @Test
    fun checkWantDayExcelVocaTest() = runBlocking {

        Mockito.`when`(excelVocaRepository.getWantDayExcelVocaData(any())).thenReturn(
            Result.success(
                mockExcelVocaEntityList
            )
        )

        val getWantDayExcelVocaDataResult = studyInteractor.getWantExcelVocaData("Day1")

        MatcherAssert.assertThat(
            "list 에 있는 ExcelData 가 1개이기 때문에 성공",
            (getWantDayExcelVocaDataResult as? Result.Success)?.value?.size,
            Matchers.`is`(
                1
            )
        )
    }


    //즐겨찾기가 toggle 이 되는지 확인.
    @Test
    fun checkToggleBookmarkTest() = runBlocking {

        val mockData = ExcelData(day = "Day1", word = "resume", mean = "이력서", like = false)

        Mockito.`when`(excelVocaRepository.toggleBookmarkExcelData(any())).thenReturn(
            Result.success(
                mockData.copy(like = true).toExcelVocaEntity()
            )
        )

        val getWantDayExcelVocaDataResult = studyInteractor.toggleBookmarkExcelData(mockData)
        MatcherAssert.assertThat(
            "bookmark 상태가 toggle 되므로 성공",
            (getWantDayExcelVocaDataResult as? Result.Success)?.value?.like,
            Matchers.`is`(
                true
            )
        )
    }


    // 날짜가 Null 일 경우 에러메세지가 올바르게 뜨는지 확인한다.
    @Test
    fun checkWantDayNullTest() = runBlocking {

        val getWantDayExcelVocaDataResult = studyInteractor.getWantExcelVocaData(null)

        MatcherAssert.assertThat(
            "wantDay 값이 null 이므로",
            (getWantDayExcelVocaDataResult as? Result.Failure)?.throwable?.message,
            Matchers.`is`(
                "dayValue is Null or Empty!"
            )
        )
    }
}