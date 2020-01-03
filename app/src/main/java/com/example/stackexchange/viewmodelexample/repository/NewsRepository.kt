package com.example.stackexchange.viewmodelexample.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.retrofit.ApiService
import com.example.stackexchange.viewmodelexample.retrofit.Config
import com.example.stackexchange.viewmodelexample.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.HashMap

class NewsRepository {
    val _newsList : MutableLiveData<MutableList<NewsModel>> = MutableLiveData()
    val newsList : LiveData<MutableList<NewsModel>> = _newsList

    @SuppressLint("CheckResult")
    fun getNews(){
        val mMapParams: MutableMap<String, Any> = HashMap()
        mMapParams["page"] = 1
        val retrofit = RetrofitClient.getClient(Config.URL)
        val apiService = retrofit!!.create(ApiService::class.java)
        apiService.getAllNews(mMapParams).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _newsList.postValue(it.data as MutableList<NewsModel>?)
                },{

                }
            )
    }
}