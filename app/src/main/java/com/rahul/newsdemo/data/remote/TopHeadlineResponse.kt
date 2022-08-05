package com.rahul.newsdemo.data.remote

import com.rahul.newsdemo.data.local.Article
import com.rahul.newsdemo.data.local.TopHeadline

data class ApiTopHeadline(
    val articles: List<ApiArticle?>?,
    val status: String?,
    val totalResults: Int?
) {
    fun toTopHeadline(): TopHeadline {
        val articleList =
            articles.orEmpty().filterNotNull().mapNotNull { apiArticle -> apiArticle.toArticle() }
        return TopHeadline(articleList, status, totalResults)
    }
}

data class ApiArticle(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) {
    fun toArticle(): Article? {
        if (title == null) return null
        return Article(author, content, description, publishedAt, title, url, urlToImage)
    }
}