package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeBinding
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds

class HomeAdapter(val context: Context, resultHomeFeeds: MutableList<ResultHomeFeeds>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val feedAdapter = FeedAdapter(context, resultHomeFeeds)
    private val storyAdapter = StoryAdapter(context)
    private val adapterlist = listOf(HomeData(storyAdapter, feedAdapter))

    inner class ViewHolder(val binding: ItemRecyclerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeData) {
            binding.recyclerHomeStory.apply {
                adapter = item.storyAdapter
                layoutManager = LinearLayoutManager(
                    binding.recyclerHomeStory.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
            binding.recyclerHomeFeed.apply {
                adapter = item.feedAdapter
                layoutManager = LinearLayoutManager(
                    binding.recyclerHomeStory.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(adapterlist[position])
    }

    override fun getItemCount(): Int {
        return adapterlist.size
    }
}

data class HomeData(
    val storyAdapter: StoryAdapter,
    val feedAdapter: FeedAdapter
)