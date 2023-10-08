package com.example.testtaskapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.MainViewHolderNewsBinding
import com.example.testtaskapp.entity.News
import com.example.testtaskapp.presentation.adapters.holders.MainListViewHolder
import com.example.testtaskapp.utils.NewsDiffCallback

class MainListAdapter() : ListAdapter<News, MainListViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val holderBinding = DataBindingUtil.inflate<MainViewHolderNewsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.main_view_holder_news,
            parent,
            false
        )
        return MainListViewHolder(holderBinding)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: MainListViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(getItem(position))
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}

