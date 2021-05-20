package com.example.englishvocabulary.data.source.remote

import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.network.response.NaverSearchResponse

interface SearchRemoteDataSource {
    fun searchKakaoWord(
        word: String,
        callback: (text: KakaoSearchResponse) -> Unit
    )

    fun searchNaverWord(
        word: String,
        callback: (text: NaverSearchResponse) -> Unit
    )

    fun detachKakaoWord(
        word: String,
        callback: (nation: KakaoDetachResponse) -> Unit
    )
}