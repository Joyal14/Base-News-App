package com.example.basicnewsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicnewsapplication.api.NewsResponse
import retrofit2.Call
import retrofit2.Response
import com.example.basicnewsapplication.databinding.ActivityMainBinding

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewsAdapter
    var pageNum = 1
    var totalResult = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", pageNum)
        news.enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val news = response.body()
                if (news != null) {
                    Log.d("News error", news.toString())
                    adapter = NewsAdapter(this@MainActivity, news.articles)
                    totalResult = news.totalResults
                    binding.newsList.adapter = adapter
                    binding.newsList.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("News error", "Error in Fetching in news")
            }
        })
    }
}