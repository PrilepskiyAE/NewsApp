package com.prilepskiy.newsapp.domain.usecase

import com.prilepskiy.newsapp.domain.interactors.GetTopNewsUseCase
import com.prilepskiy.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTopNewsUseCaseImpl(private val rep:NewsRepository): GetTopNewsUseCase {
    override suspend fun invoke(language: String) = withContext(Dispatchers.IO){
        return@withContext rep.getTopNews(language)
    }
}