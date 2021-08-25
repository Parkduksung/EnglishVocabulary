package com.example.englishvocabulary.viewmodel

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.interactor.BookmarkInteractor
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito

class HomeViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var bookmarkInteractor: BookmarkInteractor

    private lateinit var homeViewModel: HomeViewModel

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { bookmarkInteractor }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        homeViewModel = HomeViewModel(application)
        homeViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }

    @Test
    fun checkToggleBookmarkAddExcelDataSuccessTest() = runBlocking {
        val excelData = ExcelData(day = "Day1", word = "resume", mean = "이력서", like = false)

        val successResult = Result.success(excelData.copy(like = true).toExcelVocaEntity())

        Mockito.`when`(bookmarkInteractor.toggleBookmarkExcelData(excelData))
            .thenReturn(successResult)

        homeViewModel.toggleBookmark(excelData)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.AddBookmark(excelData))
    }

    @Test
    fun checkToggleBookmarkDeleteExcelDataSuccessTest() = runBlocking {
        val excelData = ExcelData(day = "Day1", word = "resume", mean = "이력서", like = true)

        val successResult = Result.success(excelData.copy(like = false).toExcelVocaEntity())

        Mockito.`when`(bookmarkInteractor.toggleBookmarkExcelData(excelData))
            .thenReturn(successResult)

        homeViewModel.toggleBookmark(excelData)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.DeleteBookmark(excelData))
    }

    @Test
    fun checkToggleBookmarkExcelDataFailTest() = runBlocking {

        val excelData = ExcelData(day = "Day1", word = "resume", mean = "이력서", like = false)

        val throwable = Throwable("Error ToggleBookmark")

        val failureResult = Result.failure<ExcelVocaEntity>(throwable)

        Mockito.`when`(bookmarkInteractor.toggleBookmarkExcelData(excelData))
            .thenReturn(failureResult)

        homeViewModel.toggleBookmark(excelData)

        Mockito.verify(viewStateObserver)
            .onChanged(HomeViewModel.HomeViewState.Error("Bookmark Error"))

    }
}