package com.rahul.newsdemo.di

import com.rahul.newsdemo.data.repository.NewsRepository
import com.rahul.newsdemo.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository = newsRepository
}