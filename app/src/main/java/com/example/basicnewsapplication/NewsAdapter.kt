package com.example.basicnewsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basicnewsapplication.api.Article

class NewsAdapter (val context: Context, val article: List<Article>):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.imgNews)
        var title = itemView.findViewById<TextView>(R.id.txtTitle)
        var desp = itemView.findViewById<TextView>(R.id.txtDes)
        var author = itemView.findViewById<TextView>(R.id.txtAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items_news,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return article.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article =article[position]
        holder.title.text = article.title
        holder.desp.text = article.description
        holder.author.text =article.author
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            Toast.makeText(context,article.title, Toast.LENGTH_LONG).show()
        }
    }
}