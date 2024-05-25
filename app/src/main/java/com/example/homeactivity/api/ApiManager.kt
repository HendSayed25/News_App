package com.example.homeactivity.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    companion object{
        private val BASE_URL="https://newsapi.org/"
        private var retrofit:Retrofit?=null
        private fun getInstance():Retrofit{
            if(retrofit==null){
                //the httpLoggingInterceptor used to check the data which send or receive between client and server to can debug and see the data
                val logging = HttpLoggingInterceptor(
                    object:HttpLoggingInterceptor.Logger{ //there i put parameter to http to see any information about api in log
                        override fun log(message: String) {
                            Log.e("api",message)
                        }

                    }
                )
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                //create retrofit
                retrofit=Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()) //this line responsible of convert from Gson to object and from object to Gson
                    .build()

            }

            return retrofit!!
        }

        fun getApis():Services{ //this function will return the Service interface
            return getInstance().create(Services::class.java)
        }


    }
}