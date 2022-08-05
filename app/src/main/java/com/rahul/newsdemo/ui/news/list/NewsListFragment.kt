package com.rahul.newsdemo.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.newsdemo.data.local.EventObserver
import com.rahul.newsdemo.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    private val viewModel: NewsListViewModel by viewModels()

    private lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewModel.fetchTopNewsHeadlines()
        startObserving()
    }

    private fun setupRecycler() {
        binding.newsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = NewsListAdapter()
        binding.newsRecycler.adapter = adapter
    }

    private fun startObserving() {
        viewModel.loading.observe(viewLifecycleOwner) {

        }

        viewModel.error.observe(viewLifecycleOwner, EventObserver {

        })

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.setArticleList(it)
        }
    }
}