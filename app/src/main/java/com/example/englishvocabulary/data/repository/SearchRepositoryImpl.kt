package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {

    override fun searchWord(word: String, callback: (text: String) -> Unit) {
        searchRemoteDataSource.searchWord(word, callback)
    }
}