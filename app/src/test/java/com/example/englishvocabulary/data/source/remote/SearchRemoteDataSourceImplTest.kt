package com.example.englishvocabulary.data.source.remote

import base.BaseTest
import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Request
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        kakaoApi = Mockito.mock(KakaoApi::class.java)
        mockKakaoApi()
        searchRemoteDataSourceImpl = SearchRemoteDataSourceImpl()
    }




    private fun mockKakaoApi() {
        Mockito.`when`(kakaoApi.search("en", "kr", "hello"))
            .thenReturn(object : Call<KakaoSearchResponse> {
                override fun execute(): Response<KakaoSearchResponse> {
                    return Response.success(KakaoSearchResponse(listOf(listOf("안녕"))))
                }

                override fun clone(): Call<KakaoSearchResponse> {
                    TODO("Not yet implemented")
                }

                override fun enqueue(callback: Callback<KakaoSearchResponse>) {
                    TODO("Not yet implemented")
                }

                override fun isExecuted(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun cancel() {
                    TODO("Not yet implemented")
                }

                override fun isCanceled(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun request(): Request {
                    TODO("Not yet implemented")
                }
            })
    }
}
