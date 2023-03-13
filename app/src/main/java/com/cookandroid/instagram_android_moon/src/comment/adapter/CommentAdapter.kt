package com.cookandroid.instagram_android_moon.src.comment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ActivityCommentBinding
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerCommentBinding
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeStoryBinding
import com.cookandroid.instagram_android_moon.src.comment.model.ResultComments
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds

class CommentAdapter(val context: Context, private val resultComments: MutableList<ResultComments> ) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemRecyclerCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultComments) {
            Glide.with(context).load(item.profilePicture).into(binding.ivCommentCommentProfileImage)
            binding.apply{
                tvCommentCommentUserName.text = item.profileName
                tvCommentCommentContent.text = item.comment
                tvCommentCommentPostDate.text = item.updatedAt
                tvCommentCommentLikeCount.text = item.likeCount.toString()
                if(item.likeCount == 0) tvCommentCommentLikeCount.visibility = View.INVISIBLE
                if(item.likeOn == 1) ckbxCommentCommentLike.isChecked = true
                ivCommentCommentProfileImage.clipToOutline = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerCommentBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultComments[position])
    }

    override fun getItemCount(): Int {
        return resultComments.size
    }
}