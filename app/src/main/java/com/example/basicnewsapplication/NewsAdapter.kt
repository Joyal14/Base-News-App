package com.example.basicnewsapplication

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basicnewsapplication.api.Article
import com.example.basicnewsapplication.databinding.ItemsNewsBinding

class NewsAdapter(private val context: Context, private val article: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    //ViewHolder class to fetch itemView
//    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var newsImage: ImageView = itemView.findViewById(R.id.imgNews)
//        var title: TextView = itemView.findViewById(R.id.txtTitle)
//        var des: TextView = itemView.findViewById(R.id.txtDes)
//        var author: TextView = itemView.findViewById(R.id.txtAuthor)
//        var url:TextView =itemView.findViewById(R.id.txt_url)
//    }
    inner class ArticleViewHolder(itemView:ItemsNewsBinding) : RecyclerView.ViewHolder(itemView.root){
        var newsImage: ImageView = itemView.imgNews
        var title: TextView = itemView.txtTitle
        var des: TextView = itemView.txtDes
        var author: TextView = itemView.txtAuthor
        var url:TextView =itemView.txtUrl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.items_news, parent, false)
//        return ArticleViewHolder(view)

        //using View Binding
        val binding = ItemsNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
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
        holder.url.text = article.publishedAt

        //for Image display need to use Glide lb retrofit not support imageView
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, article.title, Toast.LENGTH_SHORT).show()
//            val url = "https://developers.android.com"

            //Chrome custom tab using
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(context, Uri.parse(article.url))
        }
    }

    private fun anim(view: View) {
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 1400
        view.startAnimation(animation)
    }
}