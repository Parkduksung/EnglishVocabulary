package com.example.englishvocabulary.koin

import androidx.room.Room
import com.example.englishvocabulary.data.repository.*
import com.example.englishvocabulary.data.source.local.bookmark.BookmarkLocalDataSource
import com.example.englishvocabulary.data.source.local.bookmark.BookmarkLocalDataSourceImpl
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSource
import com.example.englishvocabulary.data.source.local.excelvoca.ExcelVocaLocalDataSourceImpl
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSource
import com.example.englishvocabulary.data.source.remote.SearchRemoteDataSourceImpl
import com.example.englishvocabulary.network.api.KakaoApi
import com.example.englishvocabulary.network.api.NaverApi
import com.example.englishvocabulary.network.room.database.BookmarkDatabase
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
import com.example.englishvocabulary.ui.home.bookmark.BookmarkViewModel
import com.example.englishvocabulary.ui.home.quiz.QuizViewModel
import com.example.englishvocabulary.ui.home.search.SearchViewModel
import com.example.englishvocabulary.ui.home.study.StudyViewModel
import com.example.englishvocabulary.ui.splash.SplashInteractor
import com.example.englishvocabulary.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppKoinSetup : KoinBaseSetup() {

    private val interactorModule = module {
        factory { SplashInteractor() }
    }

    private val viewModelModule = module {
        viewModel { SplashViewModel(get()) }
        viewModel { StudyViewModel(get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { QuizViewModel(get()) }
        viewModel { BookmarkViewModel(get()) }
    }

    private val repositoryModule = module {
        single<SearchRepository> { SearchRepositoryImpl() }
        single<BookmarkRepository> { BookmarkRepositoryImpl() }
        single<ExcelVocaRepository> { ExcelVocaRepositoryImpl() }
    }

    private val sourceModule = module {
        single<BookmarkLocalDataSource> { BookmarkLocalDataSourceImpl() }
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
        single {
            Room.databaseBuilder(
                get(),
                BookmarkDatabase::class.java,
                "bookmark_database"
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
            apiModule,
            databaseModule,
            viewModelModule,
            repositoryModule,
            sourceModule
        )
    }

    companion object {
        private const val KAKAO_URL = "https://dapi.kakao.com/"
        private const val NAVER_URL = "https://openapi.naver.com/"

    }
}