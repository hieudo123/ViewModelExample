package com.example.stackexchange.viewmodelexample.retrofit

class ApiBuilder {
    companion object{
        private val apiService : ApiService ?= null
        fun getApiService(): ApiService {
            if (apiService != null)
                return apiService
            val retrofitClient = RetrofitClient.getClient(Config.URL)
            return retrofitClient!!.create(ApiService::class.java)
        }
    }
}