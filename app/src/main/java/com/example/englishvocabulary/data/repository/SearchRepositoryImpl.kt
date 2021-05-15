package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {

    override fun searchWord(word: String, callback: (text: KakaoSearchResponse) -> Unit) {
        searchRemoteDataSource.searchWord(word, callback)
    }

    override fun detachWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {
        searchRemoteDataSource.detachWord(word, callback)
    }
}