package com.example.englishvocabulary.data.source.remote

interface SearchRemoteDataSource {
    fun searchWord(
        word: String,
        callback: (text: String) -> Unit
    )
}