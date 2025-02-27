package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeFeedBinding
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeStoryBinding
import com.cookandroid.instagram_android_moon.src.comment.CommentActivity
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeStories
import com.cookandroid.instagram_android_moon.src.story.StoryActivity

class StoryAdapter(val context: Context, private val resultHomeStories: MutableList<ResultHomeStories>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ItemRecyclerHomeStoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultHomeStories) {
            Glide.with(context).load(item.profile_image_url).into(binding.ivItemStoryProfileImg)
            binding.apply {
                ivItemStoryProfileImg.clipToOutline = true
                if(item.self_status == 1) tvItemStoryProfileUserName.text = context.resources.getString(R.string.myStory)
                else tvItemStoryProfileUserName.text = item.nickname
                if(item.view_status == 0) ivItemStoryViewUncheck.visibility = View.VISIBLE
                else ivItemStoryViewCheck.visibility = View.VISIBLE
                Log.d("STORYCHECK", item.view_status.toString())
            }
            binding.root.setOnClickListener {
                val intent = Intent(context, StoryActivity::class.java)
                intent.putExtra("user_id", item.user_id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerHomeStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultHomeStories[position])
    }

    override fun getItemCount(): Int {
        return resultHomeStories.size
    }
}