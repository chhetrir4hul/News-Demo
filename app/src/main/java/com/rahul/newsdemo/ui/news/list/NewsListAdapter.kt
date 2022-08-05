package com.rahul.newsdemo.ui.news.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.newsdemo.data.local.Article
import com.rahul.newsdemo.databinding.ItemNewsBinding

class NewsListAdapter : RecyclerView.Adapter<NewsListViewHolder>() {
    private var articleList: List<Article> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setArticleList(articleList: List<Article>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount() = articleList.size
}