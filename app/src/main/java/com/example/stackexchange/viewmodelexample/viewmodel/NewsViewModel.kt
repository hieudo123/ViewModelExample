package com.example.stackexchange.viewmodelexample.viewmodel

import androidx.lifecycle.LiveData
import com.example.stackexchange.viewmodelexample.base.BaseViewModel
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.repository.NewsRepository

class NewsViewModel : BaseViewModel() {

    private var newsRepository : NewsRepository = NewsRepository()
    var newsList : LiveData<MutableList<NewsModel>> = newsRepository.newsList

    fun getNews(){
        showLoading(true)
        newsRepository.getNews()
    }

}