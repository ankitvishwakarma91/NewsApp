package com.softworkshub.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.softworkshub.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Constructor
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val newsUseCases: NewsUseCases
) : ViewModel(){

    val news = newsUseCases.getNews(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)


}