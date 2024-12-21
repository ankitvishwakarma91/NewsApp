package com.softworkshub.newsapp.di

import android.app.Application
import androidx.room.Room
import com.softworkshub.newsapp.data.local.NewsDao
import com.softworkshub.newsapp.data.local.NewsDatabase
import com.softworkshub.newsapp.data.local.NewsTypeConverter
import com.softworkshub.newsapp.data.manager.LocalUserManagerImpl
import com.softworkshub.newsapp.data.remote.NewsApi
import com.softworkshub.newsapp.data.repository.NewsRepositoryImpl
import com.softworkshub.newsapp.domain.manager.LocalUserManager
import com.softworkshub.newsapp.domain.repository.NewsRepository
import com.softworkshub.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.softworkshub.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.softworkshub.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.softworkshub.newsapp.domain.usecases.news.DeleteArticle
import com.softworkshub.newsapp.domain.usecases.news.GetNews
import com.softworkshub.newsapp.domain.usecases.news.NewsUseCases
import com.softworkshub.newsapp.domain.usecases.news.SearchNews
import com.softworkshub.newsapp.domain.usecases.news.SelectArticleByUrl
import com.softworkshub.newsapp.domain.usecases.news.SelectArticles
import com.softworkshub.newsapp.domain.usecases.news.UpsertArticle
import com.softworkshub.newsapp.util.Constants.BASE_URL
import com.softworkshub.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application : Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository),
            upsertArticle = UpsertArticle(newsRepository = newsRepository),
            deleteArticle = DeleteArticle(newsRepository = newsRepository),
            selectArticles = SelectArticles(newsRepository = newsRepository),
            selectArticleByUrl = SelectArticleByUrl(newsRepository = newsRepository)
        )
    }


    @Provides
    @Singleton
    fun providesDataBase(
        application: Application,
    ):NewsDatabase{

        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME,
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao = newsDatabase.newsDao


}