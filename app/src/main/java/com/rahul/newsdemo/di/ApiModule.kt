package com.rahul.newsdemo.di

import com.rahul.newsdemo.data.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun providesNewsApiService(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)
}