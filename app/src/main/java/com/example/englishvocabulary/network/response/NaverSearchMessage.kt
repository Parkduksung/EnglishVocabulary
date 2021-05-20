package com.example.englishvocabulary.network.response

data class NaverSearchMessage(
    val service: String,
    val type: String,
    val version: String,
    val naverSearchResult: NaverSearchResult
)