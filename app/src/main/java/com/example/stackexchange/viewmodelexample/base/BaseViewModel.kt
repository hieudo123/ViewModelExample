package com.example.stackexchange.viewmodelexample.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    var isViewLoading : LiveData<Boolean> = _isLoading

    fun showLoading(isShowLoading : Boolean){
        this._isLoading.value = isShowLoading
    }
}