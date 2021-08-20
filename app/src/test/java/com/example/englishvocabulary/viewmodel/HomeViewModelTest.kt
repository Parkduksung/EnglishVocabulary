package com.example.englishvocabulary.viewmodel

import base.ViewModelBaseTest
import com.example.englishvocabulary.interactor.StudyInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock

class HomeViewModelTest : ViewModelBaseTest() {

    @Mock
    lateinit var studyInteractor: StudyInteractor

    private lateinit var homeViewModel: HomeViewModel

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { studyInteractor }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        homeViewModel = HomeViewModel(application)
        homeViewModel.viewStateLiveData.observeForever(viewStateObserver)
    }
}