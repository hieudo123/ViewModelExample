package com.example.stackexchange.viewmodelexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.viewmodelexample.base.BaseViewModel
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.repository.NewsRepository
import com.example.stackexchange.viewmodelexample.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.HashMap
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repository: NewsRepository) : BaseViewModel() {

    private val _newsList: MutableLiveData<MutableList<NewsModel>> = repository.getNewsList()
    val newsList:  MutableLiveData<MutableList<NewsModel>>
    get() = _newsList

    private val viewModelJob = Job()

    private val viewModelScope  = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        loadNews()
    }

    fun loadNews(){
        showLoading(true)
        repository.loadNews()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}