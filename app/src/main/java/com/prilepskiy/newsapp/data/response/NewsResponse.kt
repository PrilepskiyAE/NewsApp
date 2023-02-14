package com.prilepskiy.newsapp.data.response

data class NewsResponse(
    val article: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)