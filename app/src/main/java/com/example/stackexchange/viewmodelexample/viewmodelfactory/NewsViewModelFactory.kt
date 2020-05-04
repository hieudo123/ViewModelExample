package com.example.stackexchange.viewmodelexample.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stackexchange.viewmodelexample.repository.NewsRepository
import com.example.stackexchange.viewmodelexample.viewmodel.NewsViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory @Inject constructor(val newsRepository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}