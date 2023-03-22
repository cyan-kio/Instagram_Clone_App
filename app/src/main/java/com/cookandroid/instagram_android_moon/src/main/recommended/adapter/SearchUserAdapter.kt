package com.cookandroid.instagram_android_moon.src.main.recommended.adapter

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerSearchUserBinding
import com.cookandroid.instagram_android_moon.src.main.MainActivity
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionInterface
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse
import com.cookandroid.instagram_android_moon.src.main.profile.other.OtherProfileFragment
import com.cookandroid.instagram_android_moon.src.main.recommended.model.ResultSearchUser

class SearchUserAdapter(val context: Context, private val resultSearchUser: MutableList<ResultSearchUser>) :
    RecyclerView.Adapter<SearchUserAdapter.ViewHolder>(), FollowFunctionInterface {
    private val mainActivity = MainActivity()
    inner class ViewHolder(val binding: ItemRecyclerSearchUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultSearchUser) {
            // 닉네임
            binding.tvItemSearchUserUserName.text = item.nickname
            // 이름
            if(item.name != null) {
                binding.tvItemSearchUserName.apply {
                    text = item.name
                    visibility = View.VISIBLE
                }
            }
            // 이미지 삽입
            Glide.with(context).load(item.profile_image_url)
                .into(binding.ivItemSearchUserProfileImage)
            binding.ivItemSearchUserProfileImage.clipToOutline = true
            // story
            if (item.story_status == 1) {
                binding.ivItemSearchUserStoryUncheck.apply {
                    visibility = View.VISIBLE
                }
            }
            if (item.connected_count == 1) {
                binding.tvItemSearchUserConnected.text = item.connected_friend_nickname +"님이 팔로우합니다"
                binding.tvItemSearchUserConnected.visibility = View.VISIBLE
            } else if(item.connected_count > 1) {
                binding.tvItemSearchUserConnected.text = item.connected_friend_nickname +"님 외 " + item.connected_count+ "명이 팔로우합니다"
                binding.tvItemSearchUserConnected.visibility = View.VISIBLE
            }
            binding.root.setOnClickListener {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, OtherProfileFragment(item.user_id))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemRecyclerSearchUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultSearchUser[position])
    }

    override fun getItemCount(): Int {
        return resultSearchUser.size
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