package com.prilepskiy.newsapp.domain.model

import android.os.Parcelable
import com.prilepskiy.newsapp.data.response.ArticleResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Parcelable {
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
