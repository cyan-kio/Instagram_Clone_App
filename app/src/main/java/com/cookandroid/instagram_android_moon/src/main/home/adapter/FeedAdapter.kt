package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeFeedBinding
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds

class FeedAdapter(val context: Context, private val resultHomeFeeds: MutableList<ResultHomeFeeds>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecyclerHomeFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultHomeFeeds) {
            binding.tvTopHomeFeedUserName.text = item.profileName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemRecyclerHomeFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultHomeFeeds[position])
    }

    override fun getItemCount(): Int {
        return resultHomeFeeds.size
    }
}