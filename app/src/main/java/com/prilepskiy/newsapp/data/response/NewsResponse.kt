package com.prilepskiy.newsapp.data.response

data class NewsResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)