package com.softworkshub.newsapp.domain.repository

import androidx.paging.PagingData
import com.softworkshub.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface NewsRepository {

    // 3rd step
    fun getNews(sources : List<String>) : Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>) : Flow<PagingData<Article>>


    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectAllArticle() : Flow<List<Article>>

    suspend fun selectArticle(url :String) : Article?
}