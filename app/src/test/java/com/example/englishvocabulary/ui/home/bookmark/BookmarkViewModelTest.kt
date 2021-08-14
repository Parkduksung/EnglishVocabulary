package com.example.englishvocabulary.ui.home.bookmark

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock

class BookmarkViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var excelVocaRepository: ExcelVocaRepository

    override fun createModules(): List<Module> {
        return listOf(module { single { excelVocaRepository } })
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
    }
}