package com.example.englishvocabulary.ui.home.bookmark

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito

class BookmarkViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var excelVocaRepository: ExcelVocaRepository

    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun createModules(): List<Module> {
        return listOf(module { single { excelVocaRepository } })
    }

    companion object {

        private val mockExcelVocaEntityList =
            listOf(ExcelData(day = "Day1", word = "resume", mean = "이력서").toExcelVocaEntity())
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        bookmarkViewModel = BookmarkViewModel(application)
        bookmarkViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }


    @Test
    fun checkGetAllBookmarkTest() = runBlocking {


        Mockito.`when`(excelVocaRepository.getAllBookmarkList()).thenReturn(
            Result.success(
                mockExcelVocaEntityList
            )
        )

        bookmarkViewModel.getAllBookmark()
        Mockito.verify(viewStateObserver).onChanged(
            BookmarkViewModel.BookmarkViewState.BookmarkList(
                mockExcelVocaEntityList.map { it.toExcelData() })
        )

    }

    @Test
    fun checkGetAllBookmarkErrorTest() = runBlocking {

        Mockito.`when`(excelVocaRepository.getAllBookmarkList()).thenReturn(
            Result.failure(
                Throwable("bookmarkList is Null!")
            )
        )

        bookmarkViewModel.getAllBookmark()
        Mockito.verify(viewStateObserver).onChanged(
            BookmarkViewModel.BookmarkViewState.Error(
                Throwable("bookmarkList is Null!").message!!
            )
        )

    }

}