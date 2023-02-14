package com.prilepskiy.newsapp.data.response

import com.prilepskiy.newsapp.domain.model.NewsModel

data class ArticleResponse(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)

