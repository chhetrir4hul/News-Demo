package com.rahul.newsdemo.ui.news.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.rahul.newsdemo.data.local.Article
import com.rahul.newsdemo.data.local.TopHeadline
import com.rahul.newsdemo.data.repository.NewsRepository
import com.rahul.newsdemo.util.MainCoroutineRule
import com.rahul.newsdemo.util.getOrAwaitValue
import com.rahul.newsdemo.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK(relaxed = true)
    private lateinit var mockNewsRepository: NewsRepository

    private lateinit var viewModel: NewsListViewModel

    private fun getNewsHeadlines() = TopHeadline(
        articles = listOf(
            Article(
                author = "Test Author 1",
                content = "ABCD",
                description = "ABCD",
                publishedAt = "2022-08-05T00:13:30Z",
                title = "ABCD",
                url = "ABCD",
                urlToImage = "ABCD"
            )
        ),
        status = "ok",
        totalResults = 1
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = NewsListViewModel(mockNewsRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun fetchTopNewsHeadlines_success() {
        coEvery { mockNewsRepository.getTopHeadlines(any()) } returns Result.Success(
            getNewsHeadlines()
        )
        viewModel.fetchTopNewsHeadlines()
        Truth.assertThat(viewModel.news.getOrAwaitValue()).hasSize(1)
    }

    @Test
    fun fetchTopNewsHeadlines_failure() {
        coEvery { mockNewsRepository.getTopHeadlines(any()) } returns Result.Error("error")
        viewModel.fetchTopNewsHeadlines()
        Truth.assertThat(viewModel.error.getOrAwaitValue().getContentIfNotHandled()?.message)
            .isEqualTo("error")
    }
}