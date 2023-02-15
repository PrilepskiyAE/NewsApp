package com.prilepskiy.newsapp.domain.interactors

import com.prilepskiy.newsapp.core.ActionResult
import com.prilepskiy.newsapp.domain.model.NewsModel
import org.intellij.lang.annotations.Language

interface GetTopNewsUseCase {
    suspend operator fun invoke(language: String): ActionResult<List<NewsModel>>
}