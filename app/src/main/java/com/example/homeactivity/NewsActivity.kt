package com.example.homeactivity

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.homeactivity.api.ApiManager
import com.example.homeactivity.api.Constants
import com.example.homeactivity.api.NewsResponse
import com.example.homeactivity.api.SourcesItem
import com.example.homeactivity.api.SourcesResponse
import com.example.homeactivity.ui.Home.NewsAdapter
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity:AppCompatActivity() {
    lateinit var taplayout:TabLayout
    lateinit var progressBar:ProgressBar
    lateinit var recycler_view:RecyclerView
    var adapter=NewsAdapter(null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_layout)


        taplayout=findViewById(R.id.tap_layout)
        progressBar=findViewById(R.id.progress_bar)
        recycler_view=findViewById(R.id.recycler_news)
        recycler_view.adapter=adapter
        getSources()

    }
    fun getSources(){
        ApiManager.getApis().getSources(Constants.apiKey).enqueue(object:Callback<SourcesResponse>{
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

    fun getNewsBySource(source:SourcesItem){

        progressBar.isVisible=true

        ApiManager.getApis().getNews(Constants.apiKey,source.id).enqueue(object:Callback<NewsResponse>{
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