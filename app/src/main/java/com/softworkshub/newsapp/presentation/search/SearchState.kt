package com.softworkshub.newsapp.presentation.search

import androidx.paging.PagingData
import com.softworkshub.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow


data class SearchState(
    val searchQuery:String = "",
    val articles : Flow<PagingData<Article>>? = null
)