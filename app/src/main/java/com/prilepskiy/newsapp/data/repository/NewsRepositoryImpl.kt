package com.prilepskiy.newsapp.data.repository

import com.prilepskiy.newsapp.core.ActionResult
import com.prilepskiy.newsapp.core.analyzeResponse
import com.prilepskiy.newsapp.core.makeApiCall
import com.prilepskiy.newsapp.data.apiservice.NewsApiService
import com.prilepskiy.newsapp.domain.model.NewsModel
import com.prilepskiy.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(private val data:NewsApiService): NewsRepository {
    override suspend fun getTopNews(language: String): ActionResult<List<NewsModel>> {
        val result: MutableList<NewsModel> = mutableListOf()
        val apiData =  makeApiCall {
            analyzeResponse(
                data.getTopNews(country = language)
            )
        }
        return when(apiData){
            is ActionResult.Success -> {
                apiData.data.article.onEach {
                    result.add(
                        NewsModel.from(it)
                    )
                }
                ActionResult.Success(result)
            }
            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }
        }
    }
    override suspend fun getNews(query: String): ActionResult<List<NewsModel>> {
        val result: MutableList<NewsModel> = mutableListOf()
        val apiData =  makeApiCall {
            analyzeResponse(
                data.getNews(query)
            )
        }
        return when(apiData){
            is ActionResult.Success -> {
                apiData.data.article.onEach {
                    result.add(
                        NewsModel.from(it)
                    )
                }
                ActionResult.Success(result)
            }
            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }
        }
    }
}