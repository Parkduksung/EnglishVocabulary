package com.example.englishvocabulary.koin

import androidx.room.Room
import com.example.englishvocabulary.data.repository.*
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSourceImpl
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSourceImpl
import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
import com.example.englishvocabulary.viewmodel.HomeViewModel
import com.example.englishvocabulary.viewmodel.BookmarkViewModel
import com.example.englishvocabulary.viewmodel.QuizViewModel
import com.example.englishvocabulary.viewmodel.SearchViewModel
import com.example.englishvocabulary.interactor.BookmarkInteractor
import com.example.englishvocabulary.viewmodel.StudyViewModel
import com.example.englishvocabulary.interactor.SplashInteractor
import com.example.englishvocabulary.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppKoinSetup : KoinBaseSetup() {

    private val interactorModule = module {
        factory { SplashInteractor() }
        factory { BookmarkInteractor() }
    }

    private val viewModelModule = module {
        viewModel { SplashViewModel(get()) }
        viewModel { StudyViewModel(get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { QuizViewModel(get()) }
        viewModel { BookmarkViewModel(get()) }
        viewModel { HomeViewModel(get()) }
    }

    private val repositoryModule = module {
        single<SearchRepository> { SearchRepositoryImpl() }
        single<ExcelVocaRepository> { ExcelVocaRepositoryImpl() }
    }

    private val sourceModule = module {
        single<ExcelVocaLocalDataSource> { ExcelVocaLocalDataSourceImpl() }
        single<SearchRemoteDataSource> { SearchRemoteDataSourceImpl() }
    }


    private val databaseModule = module {
        single {
            Room.databaseBuilder(
                get(),
                ExcelVocaDatabase::class.java,
                "excel_voca_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    private val apiModule = module {
        single<KakaoApi> {
            Retrofit.Builder()
                .baseUrl(KAKAO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoApi::class.java)
        }
    }


    override fun getModules(): List<Module> {
        return listOf(
            interactorModule,
            apiModule,
            databaseModule,
            viewModelModule,
            repositoryModule,
            sourceModule
        )
    }

    companion object {
        const val KAKAO_URL = "https://dapi.kakao.com/"
    }
}