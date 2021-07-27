package com.example.englishvocabulary.koin

import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.api.NaverApi
import com.example.englishvocabulary.ui.splash.SplashInteractor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppKoinSetup : KoinBaseSetup() {

    private val interactorModule = module {
        factory { SplashInteractor() }
    }


    private val apiModule = module {
        single<KakaoApi> {
            Retrofit.Builder()
                .baseUrl(KAKAO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoApi::class.java)
        }
        single<NaverApi> {
            Retrofit.Builder()
                .baseUrl(NAVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverApi::class.java)
        }
    }


    override fun getModules(): List<Module> {
        return listOf(
            interactorModule,
            apiModule
        )
    }

    companion object{
        private const val KAKAO_URL = "https://dapi.kakao.com/"
        private const val NAVER_URL = "https://openapi.naver.com/"

    }
}