package com.example.stackexchange.viewmodelexample.response

import com.example.stackexchange.viewmodelexample.model.NewsModel

data class NewsResponse(
    val Success: Int,
    val `data`: List<NewsModel>
)