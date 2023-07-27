package com.example.multipleviewrecyclerview

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=tesla&from=2023-06-26&sortBy=publishedAt&apiKey=d05cf43d01b947e3873c294def5a9deb
//https://newsapi.org/v2/top-headlines?country=in&apiKey=d05cf43d01b947e3873c294def5a9deb

const val BASE_URL = "https://newsapi.org/"
const val API_KEY="d05cf43d01b947e3873c294def5a9deb"

interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country:String, @Query("page")page:Int):Call<News>
}

object NewsService{

    val newsInstance:NewsInterface
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }

}