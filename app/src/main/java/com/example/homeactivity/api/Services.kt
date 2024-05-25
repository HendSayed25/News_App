package com.example.homeactivity.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services { //it's implementation will write by retrofit
    @GET("v2/top-headlines/sources") //represent url of the api i used
    fun getSources(
        @Query("apiKey")apiKey:String?=null
    ):Call<SourcesResponse>  ///this interface it's  return is  call from sourceResponse data class

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey")apiKey:String?=null,
        @Query("sources")sources:String?=null
    ):Call<NewsResponse>
}