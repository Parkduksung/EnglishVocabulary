package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.network.response.NaverSearchResponse
import org.koin.java.KoinJavaComponent.inject

class SearchRepositoryImpl : SearchRepository {

    private val searchRemoteDataSource by inject(SearchRemoteDataSource::class.java)

    override fun searchKakaoWord(word: String, callback: (text: KakaoSearchResponse) -> Unit) {
        searchRemoteDataSource.searchKakaoWord(word, callback)
    }

    override fun detachKakaoWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {
        searchRemoteDataSource.detachKakaoWord(word, callback)
    }


}