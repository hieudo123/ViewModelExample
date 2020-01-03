package com.example.stackexchange.viewmodelexample.orthers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.appscyclone.aclibrary.view.ACRecyclerView
import com.appscyclone.aclibrary.view.ACRecyclerView.ACBaseViewHolder
import com.appscyclone.aclibrary.view.adapter.ACBaseAdapter
import com.bumptech.glide.Glide
import com.example.stackexchange.viewmodelexample.R
import com.example.stackexchange.viewmodelexample.model.NewsModel

class NewsAdapter(data: MutableList<NewsModel>?) : ACBaseAdapter<ACRecyclerView.ACBaseViewHolder<*>>(data) {
    override fun onCreateBaseViewHolder(parent: ViewGroup?, viewType: Int): ACBaseViewHolder<*> {
        val view: View = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    class NewsViewHolder(view: View?) : ACRecyclerView.ACBaseViewHolder<NewsModel>(view){
        override fun bindData(data: NewsModel?) {
            super.bindData(data)
            Glide.with(itemView.context).load(data!!.Image).into(itemView.findViewById(R.id.itemNews_ivNewsImage))
            itemView.findViewById<TextView>(R.id.itemNews_tvTitle).text = data.Title
        }
    }
}