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
import com.example.stackexchange.viewmodelexample.viewmodel.NewsViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : BaseFragment() {

    private lateinit var newsViewModel : NewsViewModel
    private lateinit var newsAdapter : NewsAdapter
    lateinit var rvNews : ACRecyclerView
    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    @SuppressLint("CheckResult")
    override fun initView(view: View) {
        showActionbar(view,"News")
        rvNews = view.findViewById(R.id.fragNews_rvNews)
        setUpNewsViewModel()
    }

    private fun setUpNewsViewModel() {
        newsViewModel = activity.let { ViewModelProviders.of(it!!).get(NewsViewModel::class.java) }
        newsViewModel.getNews()
        newsViewModel.newsList.observe(this, Observer {
            setUpRecyclerView(it)
            newsViewModel.showLoading(false)
        })
        newsViewModel.isViewLoading.observe(this, Observer {
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