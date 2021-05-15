package com.example.englishvocabulary.data.source.remote

import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor() : SearchRemoteDataSource {

    override fun searchWord(word: String, callback: (text: String) -> Unit) {
        callback("여기까지잘타")
    }
}