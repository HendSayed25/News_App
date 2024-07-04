package com.example.homeactivity.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    lateinit var viewModel:NewsViewModel
    var adapter= NewsAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel=ViewModelProvider(this).get(NewsViewModel::class.java)

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

        taplayout=requireView().findViewById(R.id.tap_layout)
        progressBar=requireView().findViewById(R.id.progress_bar)
        recycler_view=requireView().findViewById(R.id.recycler_news)
        recycler_view.adapter=adapter

        viewModel.getSources(category)

        observeLiveData()
    }
    fun observeLiveData(){

        viewModel.SourcesLiveData.observe(viewLifecycleOwner) {
            addSourcesToTapLayout(it)
        }
        viewModel.NewsLiveData.observe(viewLifecycleOwner){
            adapter.changeData(it);
        }
        viewModel.progressBar.observe(viewLifecycleOwner){
            progressBar.isVisible=it
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
        }
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
                viewModel.getNewsBySource(source)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {//when select the tab again
                //this line enable me to get the source of the news of the tab i click to it, when i click to it can view the all news from this source
                var source=tab?.tag as SourcesItem
                viewModel.getNewsBySource(source)

            }

        })

        //to appear default data when i not click to specific tab
        taplayout.getTabAt(0)?.select()

    }



}