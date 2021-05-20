package com.example.englishvocabulary.network.response

data class NaverSearchResult(
    val srcLangType: String,
    val tarLangType: String,
    val translatedText: String
)