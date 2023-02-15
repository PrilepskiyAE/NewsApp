package com.prilepskiy.newsapp.domain.interactors

import com.prilepskiy.newsapp.core.ActionResult
import com.prilepskiy.newsapp.domain.model.NewsModel

interface GetNewsUseCase {
    suspend operator fun invoke(query:String): ActionResult<List<NewsModel>>
}