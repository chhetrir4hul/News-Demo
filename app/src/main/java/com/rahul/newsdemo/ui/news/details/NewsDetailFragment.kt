package com.rahul.newsdemo.ui.news.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rahul.newsdemo.data.local.Article
import com.rahul.newsdemo.databinding.FragmentNewsDetailsBinding
import com.rahul.newsdemo.extensions.formatNewsTime
import com.rahul.newsdemo.extensions.loadImage
import com.rahul.newsdemo.ui.main.MainActivity

class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailsBinding

    private val args: NewsDetailFragmentArgs by navArgs()

    private var article: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        article = args.article

        val activity = activity as MainActivity
        activity.supportActionBar?.title = article?.title

        binding.ivImage.loadImage(article?.urlToImage)
        binding.tvTitle.text = article?.title ?: "-"
        binding.tvAuthor.text = article?.author ?: "-"
        binding.tvPublishedDate.text = article?.publishedAt?.formatNewsTime()
        binding.tvContent.text = article?.content
    }
}