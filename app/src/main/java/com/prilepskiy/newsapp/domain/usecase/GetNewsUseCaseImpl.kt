package com.prilepskiy.newsapp.domain.usecase

import com.prilepskiy.newsapp.domain.interactors.GetNewsUseCase
import com.prilepskiy.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNewsUseCaseImpl(private val rep: NewsRepository): GetNewsUseCase {
    override suspend fun invoke(query: String) = withContext(Dispatchers.IO){
        return@withContext rep.getNews(query)
    }
}