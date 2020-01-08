package com.example.stackexchange.viewmodelexample.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private var isLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun showLoading(isShowLoading : Boolean){
        this.isLoading.value = isShowLoading
    }

    fun isLoading() = isLoading
}