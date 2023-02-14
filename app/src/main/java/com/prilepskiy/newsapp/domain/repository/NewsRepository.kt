package com.prilepskiy.newsapp.domain.repository

import com.prilepskiy.newsapp.core.ActionResult
import com.prilepskiy.newsapp.domain.model.NewsModel
import org.intellij.lang.annotations.Language
import retrofit2.http.Query

interface NewsRepository {
    suspend fun getTopNews(language: String):ActionResult<List<NewsModel>>
    suspend fun getNews(query: String):ActionResult<List<NewsModel>>
}