package com.cookandroid.instagram_android_moon.src.main.recommended.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerRecommendedGridBinding
import com.cookandroid.instagram_android_moon.src.main.profile.other.OtherProfileFragment
import com.cookandroid.instagram_android_moon.src.main.recommended.RecommendedFragment
import com.cookandroid.instagram_android_moon.src.main.recommended.model.ResultRecommended
import com.cookandroid.instagram_android_moon.src.main.recommended.view.ViewFragment

class RecommendedGridAdapter(val context: Context, private val resultRecommended: MutableList<ResultRecommended>) : RecyclerView.Adapter<RecommendedGridAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemRecyclerRecommendedGridBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultRecommended) {
            Glide.with(context).load(item.firstPhotoUrl).into(binding.ivItemRecommendedImage)
            binding.root.setOnClickListener {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .add(R.id.container_main, ViewFragment(item.postId))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerRecommendedGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultRecommended[position])
    }

    override fun getItemCount(): Int {
        return resultRecommended.size
    }
}