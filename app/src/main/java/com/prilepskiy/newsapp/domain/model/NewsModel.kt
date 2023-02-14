package com.prilepskiy.newsapp.domain.model

import com.prilepskiy.newsapp.data.response.ArticleResponse

data class NewsModel(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) {
    companion object {
        fun from(data: ArticleResponse): NewsModel = with(data) {
            NewsModel(
                author,
                content,
                description,
                publishedAt,
                title,
                url,
                urlToImage
            )
        }
    }
}
