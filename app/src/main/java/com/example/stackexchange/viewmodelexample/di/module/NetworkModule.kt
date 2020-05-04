package com.example.stackexchange.viewmodelexample.di.module

import android.app.Application
import com.example.stackexchange.viewmodelexample.di.component.ActivityScope
import com.example.stackexchange.viewmodelexample.retrofit.ApiService
import com.example.stackexchange.viewmodelexample.retrofit.Config
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @ActivityScope
    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient? {
        val cacheDir = File(app.cacheDir, "http")
        return OkHttpClient.Builder()
            .readTimeout(4, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(4, TimeUnit.MINUTES)
            .build()
    }

    @ActivityScope
    @Provides
    fun createRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(4, TimeUnit.MINUTES)
            .writeTimeout(4, TimeUnit.MINUTES)
        return Retrofit.Builder()
            .baseUrl(Config.URL)
            .client(httpClient.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    internal fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}