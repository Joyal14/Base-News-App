package com.example.basicnewsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basicnewsapplication.api.Article

class NewsAdapter(private val context: Context, private val article: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage: ImageView = itemView.findViewById(R.id.imgNews)
        var title: TextView = itemView.findViewById(R.id.txtTitle)
        var des: TextView = itemView.findViewById(R.id.txtDes)
        var author: TextView = itemView.findViewById(R.id.txtAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items_news, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return article.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        anim(holder.itemView)
        val article = article[position]
        holder.title.text = article.title
        holder.des.text = article.description
        holder.author.text = article.author
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, article.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun anim(view: View) {
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 1600
        view.startAnimation(animation)
    }
}