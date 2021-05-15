package com.example.englishvocabulary.data.repository

interface SearchRepository {

    fun searchWord(
        word: String,
        callback: (text: String) -> Unit
    )
}