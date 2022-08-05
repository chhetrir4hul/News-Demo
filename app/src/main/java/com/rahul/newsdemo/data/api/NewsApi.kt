package com.rahul.newsdemo.data.api

import com.rahul.newsdemo.data.remote.ApiTopHeadline
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines?sortBy=publishedAt")
    suspend fun getTopHeadlines(@Query("country") country: String): ApiTopHeadline
}