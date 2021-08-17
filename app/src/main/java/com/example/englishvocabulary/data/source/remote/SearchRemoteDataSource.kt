package com.example.englishvocabulary.data.source.remote

import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result

interface SearchRemoteDataSource {
    suspend fun searchKakaoWord(
        word: String?
    ): Result<KakaoSearchResponse>

}