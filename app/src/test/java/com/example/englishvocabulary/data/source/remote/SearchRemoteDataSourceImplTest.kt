package com.example.englishvocabulary.data.source.remote

import base.BaseTest
import com.example.englishvocabulary.network.api.KakaoApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock

class SearchRemoteDataSourceImplTest : BaseTest() {

    @Mock
    lateinit var kakaoApi: KakaoApi

    private lateinit var searchRemoteDataSourceImpl: SearchRemoteDataSource

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { kakaoApi }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        searchRemoteDataSourceImpl = SearchRemoteDataSourceImpl()
    }
}