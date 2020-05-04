package com.example.stackexchange.viewmodelexample.retrofit

import com.example.stackexchange.viewmodelexample.data.response.NewsResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiService {
    @GET("GetAllNews.php")
    fun getAllNews(@QueryMap body: MutableMap<String, Any>): Observable<NewsResponse>

    @GET("GetAllNews.php")
    fun getAllNewsWithCoroutine (@QueryMap body: MutableMap<String, Any>): Deferred<NewsResponse>
}