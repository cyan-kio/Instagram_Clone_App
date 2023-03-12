package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeFeedBinding
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeStoryBinding

class StoryAdapter(val context: Context) : RecyclerView.Adapter<StoryAdapter.ViewHolder>(){
    private val userlist = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    inner class ViewHolder(val binding: ItemRecyclerHomeStoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
                binding.tvItemStoryProfileUserName.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerHomeStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(userlist[position])
    }

    override fun getItemCount(): Int {
        return userlist.size
    }
}