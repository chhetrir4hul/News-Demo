package com.rahul.newsdemo.ui.news.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.newsdemo.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    fun fetchTopNewsHeadlines() {
        viewModelScope.launch {
            newsRepository.getTopHeadlines("au")
        }
    }
}