package com.softworkshub.newsapp.domain.usecases.news

import com.softworkshub.newsapp.data.local.NewsDao
import com.softworkshub.newsapp.domain.model.Article
import com.softworkshub.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

     operator fun invoke() : Flow<List<Article>> {
        return newsRepository.selectAllArticle()
    }
}