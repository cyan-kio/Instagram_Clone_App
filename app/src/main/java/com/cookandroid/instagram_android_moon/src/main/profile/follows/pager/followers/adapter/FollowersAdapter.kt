package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerFollowersBinding
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionInterface
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionService
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.model.Followers

class FollowersAdapter(val context: Context, private val followers: MutableList<Followers>, val _userId: String) :
    RecyclerView.Adapter<FollowersAdapter.ViewHolder>(), FollowFunctionInterface {
    inner class ViewHolder(val binding: ItemRecyclerFollowersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Followers) {
            // 닉네임
            binding.tvItemFollowersUserName.text = item.nickname
            // 이름
            binding.tvItemFollowersName.text = item.name
            // 이미지 삽입
            Glide.with(context).load(item.profile_image_url)
                .into(binding.ivItemFollowersProfileImage)
            binding.ivItemFollowersProfileImage.clipToOutline = true
            // follow / following 버튼
            if (item.follow_status == 1) binding.btnItemFollowersUnfollow.visibility = View.VISIBLE
            else binding.btnItemFollowersFollow.visibility = View.VISIBLE
            binding.btnItemFollowersUnfollow.setOnClickListener {
                FollowFunctionService(this@FollowersAdapter).tryUnFollow(_userId.toInt(), item.user_id)
                it.visibility = View.GONE
                binding.btnItemFollowersFollow.visibility = View.VISIBLE
            }
            binding.btnItemFollowersFollow.setOnClickListener {
                FollowFunctionService(this@FollowersAdapter).tryFollow(
                    _userId.toInt(),
                    item.user_id
                )
                it.visibility = View.GONE
                binding.btnItemFollowersUnfollow.visibility = View.VISIBLE
            }
            // story
            if (item.story_status == 1) {
                binding.ivItemFollowersStoryUncheck.apply {
                    visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemRecyclerFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(followers[position])
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    override fun onPostFollowSuccess(response: FollowResponse) {
        val result = response.result
        if(result.status == 1) Toast.makeText(context, "팔로우 성공", Toast.LENGTH_SHORT).show()
        else Toast.makeText(context, "팔로우 실패", Toast.LENGTH_SHORT).show()
    }

    override fun onPostFollowFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignUpError", message)
    }

    override fun onPostUnFollowSuccess(response: FollowResponse) {
        val result = response.result
        if(result.status == 0) Toast.makeText(context, "언팔로우 성공", Toast.LENGTH_SHORT).show()
        else Toast.makeText(context, "언팔로우 실패", Toast.LENGTH_SHORT).show()
    }

    override fun onPostUnFollowFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignUpError", message)
    }
}