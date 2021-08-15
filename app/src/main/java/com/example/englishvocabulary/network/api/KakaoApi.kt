package com.example.englishvocabulary.network.api

import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.util.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {

    companion object {
        private const val REST_API_KEY = "cfbb8a5beb6e148046b520d35e7b5e94"
        private const val SEARCH = "v2/translation/translate.json"
        private const val DETACH = "v3/translation/language/detect.json"
    }

    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET(SEARCH)
    fun search(
        @Query("src_lang") inputNation: String,
        @Query("target_lang") translateNation: String,
        @Query("query") word: String
    ): Call<KakaoSearchResponse>


    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET(DETACH)
    fun detach(
        @Query("query") word: String
    ): Call<KakaoDetachResponse>
}