package com.example.basicnewsapplication

import com.example.basicnewsapplication.api.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//store the api key and base url
const val API_KEY = "ee1bf3666a2341c287089bbbc798e29f"
const val BASE_URL = "https://newsapi.org"

interface NewsInterface {

    //
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int
    ): Call<NewsResponse>
}

object NewsService {
    val newsInstance: NewsInterface

    //Retrofit connection to interface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}