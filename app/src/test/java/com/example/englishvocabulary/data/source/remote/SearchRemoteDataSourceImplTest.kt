package com.example.englishvocabulary.data.source.remote

import base.BaseTest
import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
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


    @Test
    fun checkSearchKakaoWordSuccessTest() = runBlocking {

        val successResult = Result.success(KakaoSearchResponse(listOf(listOf("안녕"))))

        Assert.assertTrue(kakaoApi.search("en", "kr", "hello").execute().code() == 200)

        MatcherAssert.assertThat(
            (searchRemoteDataSourceImpl.searchKakaoWord("hello") as Result.Success).value.translated_text,
            Matchers.`is`((successResult as Result.Success).value.translated_text)
        )
    }

    @Test
    fun checkSearchKakaoWordFailNullTest() = runBlocking {

        val failureResult = Throwable("Not Search Word")

        MatcherAssert.assertThat(
            (searchRemoteDataSourceImpl.searchKakaoWord(null) as Result.Failure).throwable.message,
            Matchers.`is`(failureResult.message)
        )
    }

    @Test
    fun checkSearchKakaoWordFailBlankTest() = runBlocking {

        val failureResult = Throwable("Not Search Word")

        MatcherAssert.assertThat(
            (searchRemoteDataSourceImpl.searchKakaoWord("") as Result.Failure).throwable.message,
            Matchers.`is`(failureResult.message)
        )

    }

    @Test
    fun checkSearchKakaoWordFailExceptionTest() = runBlocking {

        val failureResult = Throwable("Exception Error")

        Mockito.`when`(kakaoApi.search("en", "kr", "hello"))
            .then { failureResult }

        MatcherAssert.assertThat(
            (searchRemoteDataSourceImpl.searchKakaoWord("!@~#") as Result.Failure).throwable.message,
            Matchers.`is`(failureResult.message)
        )

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
