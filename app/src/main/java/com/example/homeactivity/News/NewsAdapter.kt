package com.example.homeactivity.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeactivity.R
import com.example.homeactivity.api.ArticlesItem
import com.example.homeactivity.databinding.NewsItemBinding
import com.makeramen.roundedimageview.RoundedImageView

class NewsAdapter(var items:List<ArticlesItem?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    class ViewHolder(val itemBinding:NewsItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item:ArticlesItem?){
            itemBinding.itemOfNews=item
            itemBinding.invalidateAll()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var itemBind:NewsItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.news_layout,parent,false)
        return ViewHolder(itemBind)
    }

    override fun getItemCount(): Int {
        return items?.size?:0

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var news= items?.get(position)
        holder.bind(news!!)


    }
    fun changeData(articles: List<ArticlesItem?>?){
        items=articles
        notifyDataSetChanged()
    }
}