package com.example.stackexchange.viewmodelexample.view.news

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appscyclone.aclibrary.view.ACRecyclerView
import com.example.stackexchange.viewmodelexample.R
import com.example.stackexchange.viewmodelexample.base.BaseFragment
import com.example.stackexchange.viewmodelexample.model.NewsModel
import com.example.stackexchange.viewmodelexample.orthers.adapters.NewsAdapter
import com.example.stackexchange.viewmodelexample.repository.NewsRepository
import com.example.stackexchange.viewmodelexample.viewmodel.NewsViewModel
import com.example.stackexchange.viewmodelexample.viewmodelfactory.NewsViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.ArrayList
import javax.inject.Inject


class NewsFragment : BaseFragment() {
//    @Inject
//    lateinit var newsRepository : NewsRepository
    @Inject
    lateinit var newsViewModelFactory : NewsViewModelFactory

    private lateinit var newsAdapter : NewsAdapter
    private lateinit var rvNews : ACRecyclerView
    private lateinit var newsData : MutableList<NewsModel>


    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    @SuppressLint("CheckResult")
    override fun initView(view: View) {
        getActivityComponent().inject(this)
        showActionbar(view, "News")
        rvNews = view.findViewById(R.id.fragNews_rvNews)
        newsData = ArrayList()
        setUpRecyclerView(newsData)
        setUpNewsViewModel()
    }

    private fun setUpNewsViewModel() {
//        newsViewModelFactory = NewsViewModelFactory(newsRepository)
        val newsViewModel = activity.let { ViewModelProviders.of(it!!,newsViewModelFactory).get(NewsViewModel::class.java) }
        newsViewModel.loadNews()
        newsViewModel.newsList.observe(this, Observer {
            newsData.addAll(it)
            newsAdapter.notifyDataSetChanged()
            newsViewModel.showLoading(false)
        })
        newsViewModel.isLoading().observe(this, Observer {
            if (it)
                showLoading()
            else
                hideLoading()
        })
    }

    private fun setUpRecyclerView(data : MutableList<NewsModel>){
        newsAdapter = NewsAdapter(data)
        rvNews.setLayoutManager(LinearLayoutManager(context))
        rvNews.setHasFixedSize(true)
        rvNews.adapter = newsAdapter
    }
}