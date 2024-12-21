package com.softworkshub.newsapp.presentation.bookmark

import com.softworkshub.newsapp.domain.model.Article

data class BookMarksState(
    val article: List<Article> = emptyList()
)