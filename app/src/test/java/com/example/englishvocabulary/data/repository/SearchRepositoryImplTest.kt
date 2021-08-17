package com.example.englishvocabulary.data.repository

import base.BaseTest
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito


class SearchRepositoryImplTest : BaseTest() {

    @Mock
    lateinit var searchRemoteDataSource: SearchRemoteDataSource

    private lateinit var searchRepositoryImpl: SearchRepository

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { searchRemoteDataSource }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        searchRepositoryImpl = SearchRepositoryImpl()
    }


    @Test
    fun checkSearchKakaoWordSuccessTest() = runBlocking {

        val successResult = Result.success(KakaoSearchResponse(listOf(listOf("안녕"))))

        Mockito.`when`(searchRemoteDataSource.searchKakaoWord("hello")).thenReturn(successResult)

        MatcherAssert.assertThat(
            searchRepositoryImpl.searchKakaoWord("hello"),
            Matchers.`is`(successResult)
        )
    }

    @Test
    fun checkSearchKakaoWordFailTest() = runBlocking {

        val failureResult = Result.failure<KakaoSearchResponse>(Throwable("Exception Error"))

        Mockito.`when`(searchRemoteDataSource.searchKakaoWord("`v7@")).thenReturn(failureResult)

        MatcherAssert.assertThat(
            searchRepositoryImpl.searchKakaoWord("`v7@"),
            Matchers.`is`(failureResult)
        )
    }

    @Test
    fun checkSearchKakaoWordNullTest() = runBlocking {

        val failureResult = Result.failure<KakaoSearchResponse>(Throwable("Not Search Word"))

        Mockito.`when`(searchRemoteDataSource.searchKakaoWord(null)).thenReturn(failureResult)

        MatcherAssert.assertThat(
            searchRepositoryImpl.searchKakaoWord(null),
            Matchers.`is`(failureResult)
        )
    }
}