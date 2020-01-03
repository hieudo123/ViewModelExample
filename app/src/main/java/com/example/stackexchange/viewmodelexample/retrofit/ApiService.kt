package com.example.stackexchange.viewmodelexample.retrofit

import com.example.stackexchange.viewmodelexample.response.NewsResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("GetAllNews.php")
    fun getAllNews(@QueryMap body: MutableMap<String, Any>): Observable<NewsResponse>
}