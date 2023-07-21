package com.keren.articles

import android.app.Application
import com.keren.articles.data.ARTICLES_URL
import com.keren.articles.data.ArticlesRepositoryImpl
import com.keren.articles.data.IArticlesService
import com.keren.articles.domain.ArticlesRepository
import com.keren.articles.presentation.ArticlesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticlesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ArticlesApp)
            updateModules()
        }
    }

    private fun KoinApplication.updateModules() {
        modules(module {
            single { ArticlesRepositoryImpl(get()) } binds arrayOf(
                ArticlesRepository::class
            )

            single { provideRetrofit() }
            factory { ArticlesViewModel(get()) }
        })
    }

    private fun provideRetrofit(): IArticlesService {
        return Retrofit.Builder()
            .baseUrl(ARTICLES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(IArticlesService::class.java)

    }
}