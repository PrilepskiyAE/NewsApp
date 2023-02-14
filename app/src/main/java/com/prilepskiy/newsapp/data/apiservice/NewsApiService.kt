package com.prilepskiy.newsapp.data.apiservice

import com.prilepskiy.newsapp.data.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String = "ru",
        @Query("apiKey") apiKey: String = "81513a960bab46819043be132276d805"
    ): Response<NewsResponse>


    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") q: String = "kotlin",
        @Query("apiKey") apiKey: String = "81513a960bab46819043be132276d805"
    ): Response<NewsResponse>


}