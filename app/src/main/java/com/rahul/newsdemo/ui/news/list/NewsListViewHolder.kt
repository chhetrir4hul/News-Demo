package com.rahul.newsdemo.ui.news.list

import androidx.recyclerview.widget.RecyclerView
import com.rahul.newsdemo.data.local.Article
import com.rahul.newsdemo.databinding.ItemNewsBinding
import com.rahul.newsdemo.extensions.formatNewsTime
import com.rahul.newsdemo.extensions.loadImage

class NewsListViewHolder(private val itemNewsBinding: ItemNewsBinding) :
    RecyclerView.ViewHolder(itemNewsBinding.root) {
    fun bind(article: Article) = with(itemNewsBinding) {
        tvTitle.text = article.title
        tvPublishedAt.text = article.publishedAt?.formatNewsTime()
        ivImage.loadImage(article.urlToImage)
    }
}