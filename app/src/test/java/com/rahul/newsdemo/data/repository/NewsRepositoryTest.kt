package com.rahul.newsdemo.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rahul.newsdemo.data.api.NewsApi
import com.rahul.newsdemo.data.local.TopHeadline
import com.rahul.newsdemo.data.remote.ApiArticle
import com.rahul.newsdemo.data.remote.ApiTopHeadline
import com.rahul.newsdemo.util.MainCoroutineRule
import com.rahul.newsdemo.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK(relaxed = true)
    private lateinit var newsApi: NewsApi

    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        newsRepository = NewsRepositoryImpl(newsApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getTopHeadlines_failure(): Unit = runBlocking {
        coEvery { newsApi.getTopHeadlines(any()) } throws Exception()
        val response = newsRepository.getTopHeadlines("au")
        val error = Result.Error(message = "Unknown error")
        assertThat(response).isEqualTo(error)
    }

    @Test
    fun getTopHeadlines_empty_response(): Unit = runBlocking {
        val apiTopHeadline = ApiTopHeadline(null, null, null)
        coEvery { newsApi.getTopHeadlines(any()) } returns apiTopHeadline
        val response = newsRepository.getTopHeadlines("au")
        assertThat(response).isEqualTo(Result.Success(TopHeadline(emptyList(), null, null)))
    }

    @Test
    fun getTopHeadlines_success(): Unit = runBlocking {
        val apiArticles = listOf(
            ApiArticle(null, null, null, null, "Title 1", null, null),
            ApiArticle(null, null, null, null, null, null, null),
        )
        val apiTopHeadline = ApiTopHeadline(apiArticles, null, null)
        coEvery { newsApi.getTopHeadlines(any()) } returns apiTopHeadline
        val response = newsRepository.getTopHeadlines("au")
        assertThat((response as Result.Success).data?.articles).hasSize(1)
    }
}