package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerFollowingsBinding
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionInterface
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.FollowFunctionService
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.model.Followings
import com.cookandroid.instagram_android_moon.src.main.profile.other.OtherProfileFragment

class FollowingsAdapter(val context: Context, private val followings: MutableList<Followings>, val _userId: String) : RecyclerView.Adapter<FollowingsAdapter.ViewHolder>(),
    FollowFunctionInterface {
    inner class ViewHolder(val binding: ItemRecyclerFollowingsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Followings) {
            // 닉네임
            binding.tvItemFollowingsUserName.text = item.nickname
            // 이름
            binding.tvItemFollowingsName.text = item.name
            // 이미지 삽입
            Glide.with(context).load(item.profile_image_url)
                .into(binding.ivItemFollowingsProfileImage)
            binding.ivItemFollowingsProfileImage.clipToOutline = true
            // follow / following 버튼
            if (item.follow_status == 1) binding.btnItemFollowingsUnfollow.visibility = View.VISIBLE
            else binding.btnItemFollowingsFollow.visibility = View.VISIBLE
            binding.btnItemFollowingsUnfollow.setOnClickListener {
                FollowFunctionService(this@FollowingsAdapter).tryUnFollow(
                    _userId.toInt(),
                    item.user_id
                )
                it.visibility = View.GONE
                binding.btnItemFollowingsFollow.visibility = View.VISIBLE
            }
            binding.btnItemFollowingsFollow.setOnClickListener {
                FollowFunctionService(this@FollowingsAdapter).tryFollow(
                    _userId.toInt(),
                    item.user_id
                )
                it.visibility = View.GONE
                binding.btnItemFollowingsUnfollow.visibility = View.VISIBLE
            }
            // story
            if (item.story_status == 1) {
                binding.ivItemFollowingsStoryUncheck.apply {
                    visibility = View.VISIBLE
                }
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
        val view = ItemRecyclerFollowingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(followings[position])
    }

    override fun getItemCount(): Int {
        return followings.size
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