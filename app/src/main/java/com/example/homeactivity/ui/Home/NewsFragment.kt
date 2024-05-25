package com.example.homeactivity.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeactivity.R

class NewsFragment:Fragment() {
    lateinit var catogry:Category

    companion object{
        fun getInstance(category:Category):NewsFragment{
            val fragment=NewsFragment()
            fragment.catogry=category

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_layout,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*private fun getNewsSource(){
        ApiManager.getApis().getNews(Constants.apiKey).enqueue(object:Callback<SourcesResponse>{
            override fun onResponse(p0: Call<SourcesResponse>, p1: Response<SourcesResponse>) {
             Log.e("response",p1.body().toString())
            }

            override fun onFailure(p0: Call<SourcesResponse>, p1: Throwable) {

            }

        })
    }*/
}