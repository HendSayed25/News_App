package com.example.homeactivity.News

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeactivity.Categories.Category
import com.example.homeactivity.api.ApiManager
import com.example.homeactivity.api.ArticlesItem
import com.example.homeactivity.api.Constants
import com.example.homeactivity.api.NewsResponse
import com.example.homeactivity.api.SourcesItem
import com.example.homeactivity.api.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {

    val SourcesLiveData=MutableLiveData<List<SourcesItem?>?>()
    val NewsLiveData=MutableLiveData<List<ArticlesItem?>?>()
    val progressBar=MutableLiveData<Boolean>()
    val messageLiveData=MutableLiveData<String>()//if happen any failure

    fun getSources(category: Category){
        ApiManager.getApis().getSources(Constants.apiKey,category.id).enqueue(object:
            Callback<SourcesResponse> {
            override fun onResponse(p0: Call<SourcesResponse>, p1: Response<SourcesResponse>) {
                progressBar.value=false

                SourcesLiveData.value=p1.body()?.sources

                // Log.e("data",p1.body().toString())
            }

            override fun onFailure(p0: Call<SourcesResponse>, p1: Throwable) {
                // Log.e("error",p1.localizedMessage)
            }
        })
    }


    fun getNewsBySource(source: SourcesItem){

        progressBar.value=true

        ApiManager.getApis().getNews(Constants.apiKey,source.id).enqueue(object:
            Callback<NewsResponse> {
            override fun onResponse(p0: Call<NewsResponse>, p1: Response<NewsResponse>) {

                progressBar.value=false

                NewsLiveData.value=p1.body()?.articles

            }

            override fun onFailure(p0: Call<NewsResponse>, p1: Throwable) {
                progressBar.value=false
                messageLiveData.value=p1.localizedMessage
            }

        })
    }
}