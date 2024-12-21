package com.softworkshub.newsapp.domain.usecases.news

import com.softworkshub.newsapp.data.local.NewsDao
import com.softworkshub.newsapp.domain.model.Article
import com.softworkshub.newsapp.domain.repository.NewsRepository

class SelectArticleByUrl(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String) : Article? {
        return newsRepository.selectArticle(url = url)
    }
}