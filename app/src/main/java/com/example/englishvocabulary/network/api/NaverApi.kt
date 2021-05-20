package com.example.englishvocabulary.network.api

import com.example.englishvocabulary.network.response.NaverSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface NaverApi {

    companion object {
        const val CLIENT_ID = "vwMsVxpzpfuRo5mLPimW"
        const val CLIENT_SECRET = "tP8niT4fQY"
    }

    @POST("v1/papago/n2mt.json")
    fun search(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") secret: String,
        @Query("source") source: String,
        @Query("target") target: String,
        @Query("text") text: String
    ): Call<NaverSearchResponse>

}