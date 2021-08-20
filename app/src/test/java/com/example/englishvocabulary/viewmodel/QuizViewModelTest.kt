package com.example.englishvocabulary.viewmodel

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito

class QuizViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var excelVocaRepository: ExcelVocaRepository


    private lateinit var quizViewModel: QuizViewModel

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
        quizViewModel = QuizViewModel(application)
        quizViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }

    @Test
    fun check1() = runBlocking {

        Mockito.`when`(excelVocaRepository.getAllExcelData())
            .thenReturn(Result.failure(Throwable("Error GetAllExcelEntity")))

        quizViewModel.getQuizList()

        Mockito.verify(viewStateObserver)
            .onChanged(QuizViewModel.QuizViewState.Error("Error GetAllExcelEntity"))

    }

    @Test
    fun check2() = runBlocking {

        val mockExcelVocaEntityList = mutableListOf<ExcelVocaEntity>().apply {
            for (i in 0..39) {
                add(ExcelData(day = "Day1", word = "resume", mean = "이력서").toExcelVocaEntity())
            }
        }

        val toShuffledAndChunkedList =
            mockExcelVocaEntityList.map { it.toExcelData() }.chunked(4).subList(0, 10)

        Mockito.`when`(excelVocaRepository.getAllExcelData())
            .thenReturn(Result.success(mockExcelVocaEntityList))


        quizViewModel.getQuizList()

        Mockito.verify(viewStateObserver)
            .onChanged(QuizViewModel.QuizViewState.QuizList(toShuffledAndChunkedList))

    }
}