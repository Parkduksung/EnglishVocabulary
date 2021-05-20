package com.example.englishvocabulary.data.source.remote

import android.util.Log
import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.api.NaverApi
import com.example.englishvocabulary.network.api.NaverApi.Companion.CLIENT_ID
import com.example.englishvocabulary.network.api.NaverApi.Companion.CLIENT_SECRET
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import com.example.englishvocabulary.network.response.NaverSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val kakaoApi: KakaoApi,
    private val naverApi: NaverApi
) :
    SearchRemoteDataSource {

    override fun searchKakaoWord(word: String, callback: (text: KakaoSearchResponse) -> Unit) {

        kakaoApi.search("en", "kr", word).enqueue(object : Callback<KakaoSearchResponse> {
            override fun onResponse(
                call: Call<KakaoSearchResponse>,
                response: Response<KakaoSearchResponse>
            ) {
                response.body()?.let(callback)
            }

            override fun onFailure(call: Call<KakaoSearchResponse>, t: Throwable) {
            }
        })
    }


    override fun searchNaverWord(word: String, callback: (text: NaverSearchResponse) -> Unit) {

        naverApi.search(CLIENT_ID, CLIENT_SECRET, "en", "ko", word)
            .enqueue(object : Callback<NaverSearchResponse> {
                override fun onResponse(
                    call: Call<NaverSearchResponse>,
                    response: Response<NaverSearchResponse>
                ) {
                    Log.d("결과", response.body()?.naverSearchMessage?.naverSearchResult?.translatedText ?: "null")
//                    response.body()?.naverSearchMessage?.naverSearchResult?.translatedText
                    response.body()?.let(callback)
                }

                override fun onFailure(call: Call<NaverSearchResponse>, t: Throwable) {
                    Log.d("결과", t.toString())
                }
            })
    }

    override fun detachKakaoWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {

        kakaoApi.detach(word).enqueue(object : Callback<KakaoDetachResponse> {
            override fun onResponse(
                call: Call<KakaoDetachResponse>,
                response: Response<KakaoDetachResponse>
            ) {

            }

            override fun onFailure(call: Call<KakaoDetachResponse>, t: Throwable) {

            }
        })
    }
}