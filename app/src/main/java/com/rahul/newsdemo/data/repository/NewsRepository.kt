package com.rahul.newsdemo.data.repository

import com.rahul.newsdemo.data.api.NewsApi
import com.rahul.newsdemo.data.local.TopHeadline
import com.rahul.newsdemo.extensions.toApiError
import com.rahul.newsdemo.utils.Result
import javax.inject.Inject

interface NewsRepository {
    suspend fun getTopHeadlines(countryCode: String): Result<TopHeadline>
}

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getTopHeadlines(countryCode: String): Result<TopHeadline> {
        return try {
            val response = newsApi.getTopHeadlines(countryCode)
            Result.Success(response.toTopHeadline())
        } catch (e: Exception) {
            e.toApiError()
        }
    }
}