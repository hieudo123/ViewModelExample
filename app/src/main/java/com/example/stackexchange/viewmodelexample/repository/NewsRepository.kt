package com.example.stackexchange.viewmodelexample.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.retrofit.ApiBuilder
import com.example.stackexchange.viewmodelexample.retrofit.ApiService
import com.example.stackexchange.viewmodelexample.retrofit.Config
import com.example.stackexchange.viewmodelexample.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

class NewsRepository @Inject constructor(private val  apiService: ApiService) {
    private val _newsList : MutableLiveData<MutableList<NewsModel>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadNews(){
        val mMapParams: MutableMap<String, Any> = HashMap()
        mMapParams["page"] = 1
        apiService.getAllNews(mMapParams).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _newsList.postValue(it.data as MutableList<NewsModel>?)
                },{

                }
            )
    }

    fun getNewsList()= _newsList

}