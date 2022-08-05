package com.rahul.newsdemo.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.newsdemo.data.local.Article
import com.rahul.newsdemo.data.local.Event
import com.rahul.newsdemo.data.repository.NewsRepository
import com.rahul.newsdemo.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>>
        get() = _news

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Event<Result.Error>>()
    val error: LiveData<Event<Result.Error>>
        get() = _error

    fun fetchTopNewsHeadlines() {
        viewModelScope.launch {
            _loading.value = true
            val response = newsRepository.getTopHeadlines("au")
            _loading.value = false
            when (response) {
                is Result.Error -> _error.value = Event(response)
                is Result.Success -> _news.value = response.data?.articles.orEmpty()
            }
        }
    }
}