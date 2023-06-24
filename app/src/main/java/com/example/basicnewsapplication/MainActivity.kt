package com.example.basicnewsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicnewsapplication.api.NewsResponse
import retrofit2.Call
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView
import com.example.basicnewsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val news = response.body()
                if (news != null) {
                    Log.d("News error", news.toString())
                    adapter = NewsAdapter(this@MainActivity,news.articles)
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