package com.example.englishvocabulary.data.source.remote

import android.util.Log
import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.response.KakaoDetachResponse
import com.example.englishvocabulary.network.response.KakaoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) :
    SearchRemoteDataSource {

    override fun searchWord(word: String, callback: (text: KakaoSearchResponse) -> Unit) {

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

    override fun detachWord(word: String, callback: (nation: KakaoDetachResponse) -> Unit) {

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