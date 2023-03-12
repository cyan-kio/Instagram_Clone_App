package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemPagerHomeFeedBinding
import com.cookandroid.instagram_android_moon.src.main.home.model.PhotoHomeFeeds

class HomeFeedPagerAdapter(
    private val context: Context,
    private val photoList: MutableList<PhotoHomeFeeds>
) : RecyclerView.Adapter<HomeFeedPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding: ItemPagerHomeFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoHomeFeeds) {
            Glide.with(context).load(item.photoUrl).into(binding.ivPagerHomeFeed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view =
            ItemPagerHomeFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        return holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}
