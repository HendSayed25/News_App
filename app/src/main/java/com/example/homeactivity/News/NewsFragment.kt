package com.example.homeactivity.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homeactivity.Categories.Category
import com.example.homeactivity.R
import com.example.homeactivity.api.ApiManager
import com.example.homeactivity.api.Constants
import com.example.homeactivity.api.NewsResponse
import com.example.homeactivity.api.SourcesItem
import com.example.homeactivity.api.SourcesResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment:Fragment() {

    //this function used to display the news which related to the category i click not all news
    //and use it also to send parameter to fragment
        fun getInstance(category: Category):NewsFragment{
            var fragment=NewsFragment()
            fragment.category=category
            return fragment

        }

    lateinit var taplayout: TabLayout
    lateinit var progressBar: ProgressBar
    lateinit var recycler_view: RecyclerView
    lateinit var category:Category
    var adapter= NewsAdapter(null)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_layout,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taplayout=requireView().findViewById(R.id.tap_layout)
        progressBar=requireView().findViewById(R.id.progress_bar)
        recycler_view=requireView().findViewById(R.id.recycler_news)
        recycler_view.adapter=adapter
        getSources()
    }
    fun getSources(){
        ApiManager.getApis().getSources(Constants.apiKey,category.id).enqueue(object:
            Callback<SourcesResponse> {
            override fun onResponse(p0: Call<SourcesResponse>, p1: Response<SourcesResponse>) {
                progressBar.isVisible=false

                addSourcesToTapLayout(p1.body()?.sources)

                // Log.e("data",p1.body().toString())
            }

            override fun onFailure(p0: Call<SourcesResponse>, p1: Throwable) {
                // Log.e("error",p1.localizedMessage)
            }
        })
    }

    fun  addSourcesToTapLayout(sources:List<SourcesItem?>?){

        sources!!.forEach {
            val tab=taplayout.newTab()
            tab.setText(it!!.name)
            tab.tag=it //tag is pointer refer to the tab's data
            taplayout.addTab(tab)
        }
        taplayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //this line enable me to get the source of the news of the tab i click to it, when i click to it can view the all news from this source
                var source=tab?.tag as SourcesItem
                getNewsBySource(source)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {//when select the tab again
                //this line enable me to get the source of the news of the tab i click to it, when i click to it can view the all news from this source
                var source=tab?.tag as SourcesItem
                getNewsBySource(source)
            }

        })

        //to appear default data when i not click to specific tab
        taplayout.getTabAt(0)?.select()

    }

    fun getNewsBySource(source: SourcesItem){

        progressBar.isVisible=true

        ApiManager.getApis().getNews(Constants.apiKey,source.id).enqueue(object:
            Callback<NewsResponse> {
            override fun onResponse(p0: Call<NewsResponse>, p1: Response<NewsResponse>) {

                progressBar.isVisible=false
                adapter.changeData(p1.body()?.articles)

            }

            override fun onFailure(p0: Call<NewsResponse>, p1: Throwable) {
                progressBar.isVisible=false
            }

        })

    }

}