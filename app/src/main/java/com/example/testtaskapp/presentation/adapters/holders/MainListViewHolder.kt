package com.example.testtaskapp.presentation.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskapp.databinding.MainViewHolderNewsBinding
import com.example.testtaskapp.entity.News

class MainListViewHolder(private val holderBinding: MainViewHolderNewsBinding) :
    RecyclerView.ViewHolder(holderBinding.root) {


    fun bind(news: News) {
        holderBinding.news = news
//        holderBinding.title.text = "Dsafkdskalfkladsjflkjlkj"
    }


}