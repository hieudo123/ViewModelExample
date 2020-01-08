package com.example.stackexchange.viewmodelexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.viewmodelexample.base.BaseViewModel
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.repository.NewsRepository

class NewsViewModel (private val newsRepository: NewsRepository) : BaseViewModel() {

    private val newsList : MutableLiveData<MutableList<NewsModel>> = newsRepository.getNewsList()

    init {
        loadNews()
    }

    fun loadNews(){
        showLoading(true)
        newsRepository.loadNews()
    }

    fun getNewsList() = newsList
}