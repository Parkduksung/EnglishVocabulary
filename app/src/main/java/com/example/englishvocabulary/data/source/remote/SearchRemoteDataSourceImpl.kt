package com.example.englishvocabulary.data.source.remote

import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class SearchRemoteDataSourceImpl : SearchRemoteDataSource {

    private val kakaoApi by inject(KakaoApi::class.java)

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

}