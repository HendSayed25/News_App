package com.example.homeactivity.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeactivity.R
import com.example.homeactivity.api.ArticlesItem
import com.makeramen.roundedimageview.RoundedImageView

class NewsAdapter(var items:List<ArticlesItem?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title:TextView=itemView.findViewById(R.id.title)
        var author:TextView=itemView.findViewById(R.id.author)
        var dateTime:TextView=itemView.findViewById(R.id.date_time)
        var image:RoundedImageView=itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size?:0

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var news= items?.get(position)
        holder.title.setText(news?.title)
        holder.author.setText(news?.author)
        holder.dateTime.setText(news?.publishedAt)

        Glide.with(holder.itemView).load(news?.urlToImage).into(holder.image)

    }
    fun changeData(articles: List<ArticlesItem?>?){
        items=articles
        notifyDataSetChanged()
    }
}