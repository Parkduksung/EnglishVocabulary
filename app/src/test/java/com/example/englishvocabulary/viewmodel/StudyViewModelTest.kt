package com.example.englishvocabulary.viewmodel

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.interactor.StudyInteractor
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito


class StudyViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var studyInteractor: StudyInteractor

    private lateinit var studyViewModel: StudyViewModel

    override fun createModules(): List<Module> {
        return listOf(
            module {
                factory { studyInteractor }
            }
        )
    }


    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        studyViewModel = StudyViewModel(application)
        studyViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }


    //뒤로가기 눌렀을 경우 항목화면으로 전환되는지 확인한다.
    @Test
    fun checkRouteContentTest() {
        studyViewModel.routeContent()
        Mockito.verify(viewStateObserver).onChanged(StudyViewModel.StudyViewState.RouteContent)
    }

    //날짜가 null 일 경우 확인한다.
    @Test
    fun checkDayIsNullTest() = runBlocking {
        Mockito.`when`(studyInteractor.getWantExcelVocaData(wantDay = null))
            .thenReturn(Result.failure(Throwable("dayValue is Null or Empty!")))
        studyViewModel.getAllExcelVoca(null)
        Mockito.verify(viewStateObserver)
            .onChanged(StudyViewModel.StudyViewState.Error("dayValue is Null or Empty!"))
    }

    //날짜가 empty 일 경우 확인한다.
    @Test
    fun checkDayIsEmptyTest() = runBlocking {
        Mockito.`when`(studyInteractor.getWantExcelVocaData(wantDay = ""))
            .thenReturn(Result.failure(Throwable("dayValue is Null or Empty!")))
        studyViewModel.getAllExcelVoca("")
        Mockito.verify(viewStateObserver)
            .onChanged(StudyViewModel.StudyViewState.Error("dayValue is Null or Empty!"))
    }

    //날짜가 올바르지 않을 경우 확인한다.
    @Test
    fun checkDayIsNotValidTest() = runBlocking {
        Mockito.`when`(studyInteractor.getWantExcelVocaData(wantDay = "null"))
            .thenReturn(Result.failure(Throwable("Specific Excel Voca is Empty Or Null")))

        studyViewModel.getAllExcelVoca("null")
        Mockito.verify(viewStateObserver)
            .onChanged(StudyViewModel.StudyViewState.Error("Specific Excel Voca is Empty Or Null"))
    }

    //날짜가 올바를 경우 확인한다.
    @Test
    fun checkRouteDetailTest() {
        studyViewModel.routeDetail("Day1")
        Mockito.verify(viewStateObserver)
            .onChanged(StudyViewModel.StudyViewState.RouteDetail("Day1"))
    }

    //날짜에 맞게 리스트 데이터가 잘 오는지 확인한다.
    @Test
    fun checkGetDetailDataTest() = runBlocking {

        val mockList = listOf(mockExcelData.toExcelVocaEntity())

        Mockito.`when`(studyInteractor.getWantExcelVocaData("Day1")).thenReturn(
            Result.success(
                mockList
            )
        )
        studyViewModel.getAllExcelVoca("Day1")
        Mockito.verify(viewStateObserver)
            .onChanged(StudyViewModel.StudyViewState.ExcelVoca(mockList.map { it.toExcelData() }))

    }

    //즐겨찾기 추가가 잘 되는지 확인한다.
    @Test
    fun checkAddBookmarkTest() {
        studyViewModel.addBookmark(excelData = mockExcelData)
        Mockito.verify(viewStateObserver).onChanged(
            StudyViewModel.StudyViewState.AddBookmark(
                mockExcelData
            )
        )
    }

    //즐겨찾기 삭제가 잘 되는지 확인한다.
    @Test
    fun checkDeleteBookmarkTest() {
        studyViewModel.deleteBookmark(excelData = mockExcelData)
        Mockito.verify(viewStateObserver).onChanged(
            StudyViewModel.StudyViewState.DeleteBookmark(
                mockExcelData
            )
        )
    }

    //즐겨찾기 토글이 잘 되는지 확인한다.
    @Test
    fun checkToggleBookmarkTest() {
        studyViewModel.toggleBookmark(excelData = mockExcelData)
        Mockito.verify(viewStateObserver).onChanged(
            StudyViewModel.StudyViewState.ToggleBookmark(
                mockExcelData
            )
        )
    }


    companion object {
        private val mockExcelData = ExcelData(day = "Day1", word = "resume", mean = "이력서")
    }


}