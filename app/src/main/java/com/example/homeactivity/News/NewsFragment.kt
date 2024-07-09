package com.example.homeactivity.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homeactivity.Categories.Category
import com.example.homeactivity.R
import com.example.homeactivity.api.SourcesItem
import com.example.homeactivity.databinding.NewsLayoutBinding
import com.google.android.material.tabs.TabLayout


class NewsFragment:Fragment() {

    //this function used to display the news which related to the category i click not all news
    //and use it also to send parameter to fragment
        fun getInstance(category: Category):NewsFragment{
            var fragment=NewsFragment()
            fragment.category=category
            return fragment

        }


    lateinit var category:Category
    lateinit var viewModel:NewsViewModel
    lateinit var viewBinding:NewsLayoutBinding
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
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.news_layout,container,false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.recyclerNews.adapter=adapter

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
            viewBinding.progressBar.isVisible=it
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
        }
    }



    fun  addSourcesToTapLayout(sources:List<SourcesItem?>?){

        sources!!.forEach {
            val tab=viewBinding.tapLayout.newTab()
            tab.setText(it!!.name)
            tab.tag=it //tag is pointer refer to the tab's data
            viewBinding.tapLayout.addTab(tab)
        }
        viewBinding.tapLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
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
        viewBinding.tapLayout.getTabAt(0)?.select()

    }



}