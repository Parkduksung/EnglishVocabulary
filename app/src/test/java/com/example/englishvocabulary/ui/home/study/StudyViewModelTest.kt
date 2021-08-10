package com.example.englishvocabulary.ui.home.study

import base.ViewModelBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock


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

}