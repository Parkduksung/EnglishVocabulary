package com.example.englishvocabulary.data.repository

import base.BaseTest
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock


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
}