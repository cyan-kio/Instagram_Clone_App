package com.cookandroid.instagram_android_moon.src.main.follows.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerFollowBinding
import com.cookandroid.instagram_android_moon.src.main.follows.model.Follows
import com.cookandroid.instagram_android_moon.src.main.follows.model.ResultFollower

class FollowsAdapter(val context: Context, private val follows: MutableList<Follows>) : RecyclerView.Adapter<FollowsAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ItemRecyclerFollowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Follows) {
            binding.tvItemFollowerUserName.text = item.nickname
            binding.tvItemFollowerName.text = item.name
            Glide.with(context).load(item.profile_image_url).into(binding.ivItemFollowerProfileImage)
            binding.ivItemFollowerProfileImage.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(follows[position])
    }

    override fun getItemCount(): Int {
        return follows.size
    }
}