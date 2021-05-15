package com.example.englishvocabulary.data.source.remote

import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse

interface SearchRemoteDataSource {
    fun searchWord(
        word: String,
        callback: (text: KakaoSearchResponse) -> Unit
    )

    fun detachWord(
        word: String,
        callback: (nation: KakaoDetachResponse) -> Unit
    )
}