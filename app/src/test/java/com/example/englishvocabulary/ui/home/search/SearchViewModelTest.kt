package com.example.englishvocabulary.ui.home.search

import base.ViewModelBaseTest
import com.example.englishvocabulary.data.repository.SearchRepository
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito

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


    @Test
    fun checkSearchKakaoWordTest() = runBlocking {

        searchViewModel.searchWordLiveData.postValue("hello")

        Mockito.`when`(searchRepository.searchKakaoWord("hello")).thenReturn(
            Result.Success(
                KakaoSearchResponse(listOf(listOf("안녕")))
            )
        )

        searchViewModel.searchKakaoWord()

        Mockito.verify(viewStateObserver)
            .onChanged(SearchViewModel.SearchViewstate.Translate("안녕"))

    }

    @Test
    fun checkSearchKakaoWordErrorTest() = runBlocking {

        searchViewModel.searchWordLiveData.postValue(null)

        Mockito.`when`(searchRepository.searchKakaoWord(null)).thenReturn(
            Result.Failure(
                Throwable("Not Search Word")
            )
        )

        searchViewModel.searchKakaoWord()

        Mockito.verify(viewStateObserver)
            .onChanged(SearchViewModel.SearchViewstate.Error(Throwable("Not Search Word").toString()))
    }

}