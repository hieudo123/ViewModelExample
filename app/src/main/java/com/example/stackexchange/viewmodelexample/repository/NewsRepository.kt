package com.example.stackexchange.viewmodelexample.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.viewmodelexample.di.Injector
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.retrofit.ApiBuilder
import com.example.stackexchange.viewmodelexample.retrofit.ApiService
import com.example.stackexchange.viewmodelexample.retrofit.Config
import com.example.stackexchange.viewmodelexample.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

class NewsRepository @Inject constructor(private val  apiService: ApiService) {
    private val _newsList : MutableLiveData<MutableList<NewsModel>> = MutableLiveData()

    init {
        Injector.getActivityComponent()?.inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadNews(){
        val mMapParams: MutableMap<String, Any> = HashMap()
        mMapParams["page"] = 1
//        apiService.getAllNews(mMapParams).observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                {
//                    _newsList.postValue(it.data as MutableList<NewsModel>?)
//                },{
//
//                }
//            )
        GlobalScope.launch {
            var getNewsResponse = apiService.getAllNewsWithCoroutine(mMapParams)
            try {
                var response = getNewsResponse.await()
                _newsList.postValue(response.data as MutableList<NewsModel>?)
            }catch (e: Exception){
                Log.d("getNews", e.message)
            }
        }
    }

    fun getNewsList()= _newsList

}