package com.rahul.newsdemo.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TopHeadline(
    val articles: List<Article>,
    val status: String?,
    val totalResults: Int?
)

@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String,
    val url: String?,
    val urlToImage: String?
) : Parcelable
