package com.example.testtaskapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.testtaskapp.entity.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.title == newItem.title
}