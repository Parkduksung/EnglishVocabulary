package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.network.response.NaverSearchResponse
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {

    override fun searchKakaoWord(word: String, callback: (text: KakaoSearchResponse) -> Unit) {
        searchRemoteDataSource.searchKakaoWord(word, callback)
    }

    override fun searchNaverWord(word: String, callback: (text: NaverSearchResponse) -> Unit) {
        searchRemoteDataSource.searchNaverWord(word, callback)
    }

    override fun detachKakaoWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {
        searchRemoteDataSource.detachKakaoWord(word, callback)
    }


}