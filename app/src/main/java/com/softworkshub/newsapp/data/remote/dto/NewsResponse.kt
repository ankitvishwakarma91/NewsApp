package com.softworkshub.newsapp.data.remote.dto

import com.softworkshub.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)