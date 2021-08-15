package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class SearchRepositoryImpl : SearchRepository {

    private val searchRemoteDataSource by inject(SearchRemoteDataSource::class.java)

    override suspend fun searchKakaoWord(word: String): Result<KakaoSearchResponse> =
        withContext(Dispatchers.IO) {
            return@withContext searchRemoteDataSource.searchKakaoWord(word)
        }

    override fun detachKakaoWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {
        searchRemoteDataSource.detachKakaoWord(word, callback)
    }


}