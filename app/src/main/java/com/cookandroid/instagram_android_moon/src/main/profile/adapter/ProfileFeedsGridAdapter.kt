package com.cookandroid.instagram_android_moon.src.main.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerProfileFeedsGridBinding
import com.cookandroid.instagram_android_moon.src.main.profile.ProfileFragment
import com.cookandroid.instagram_android_moon.src.main.profile.model.ResultProfileFeeds
import com.cookandroid.instagram_android_moon.src.main.profile.other.OtherProfileFragment
import com.cookandroid.instagram_android_moon.src.main.recommended.view.ViewFragment

class ProfileFeedsGridAdapter(val context: Context, private val resultProfileFeeds: MutableList<ResultProfileFeeds>) : RecyclerView.Adapter<ProfileFeedsGridAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemRecyclerProfileFeedsGridBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultProfileFeeds) {
            Glide.with(binding.ivItemProfileFeedsGrid.context).load(item.photos[0].photoUrl).into(binding.ivItemProfileFeedsGrid)
            binding.root.setOnClickListener {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .add(R.id.container_main, ViewFragment(item.postId))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerProfileFeedsGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultProfileFeeds[position])
    }

    override fun getItemCount(): Int {
        return resultProfileFeeds.size
    }
}