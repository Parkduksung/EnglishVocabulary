package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.network.response.NaverSearchResponse

interface SearchRepository {

    fun searchKakaoWord(
        word: String,
        callback: (text: KakaoSearchResponse) -> Unit
    )

    fun detachKakaoWord(
        word: String,
        callback: (nation: KakaoDetachResponse) -> Unit
    )
}