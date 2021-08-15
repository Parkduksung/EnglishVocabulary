package com.example.englishvocabulary.ui.home.search

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock

class SearchViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var searchRepository: SearchRepository

    private lateinit var searchViewModel: SearchViewModel

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { searchRepository }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        searchViewModel = SearchViewModel(application)
        searchViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }


}