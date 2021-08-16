package com.example.englishvocabulary.data.source.remote

import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.api.NaverApi
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRemoteDataSourceImpl : SearchRemoteDataSource {

    private val kakaoApi by inject(KakaoApi::class.java)
    private val naverApi by inject(NaverApi::class.java)


    override suspend fun searchKakaoWord(word: String?): Result<KakaoSearchResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                if (!word.isNullOrBlank()) {
                    Result.success(kakaoApi.search("en", "kr", word).execute().body()!!)
                } else {
                    Result.failure(Throwable("Not Search Word"))
                }
            } catch (e: Exception) {
                Result.failure(Throwable("Exception Error"))
            }
        }

    override fun detachKakaoWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {

        kakaoApi.detach(word).enqueue(object : Callback<KakaoDetachResponse> {
            override fun onResponse(
                call: Call<KakaoDetachResponse>,
                response: Response<KakaoDetachResponse>
            ) {

            }

            override fun onFailure(call: Call<KakaoDetachResponse>, t: Throwable) {

            }
        })
    }
}