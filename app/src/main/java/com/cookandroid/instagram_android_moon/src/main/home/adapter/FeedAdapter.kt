package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerHomeFeedBinding
import com.cookandroid.instagram_android_moon.src.comment.CommentActivity
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.LikePostingInterface
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.LikePostingService
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class FeedAdapter(val context: Context, private val resultHomeFeeds: MutableList<ResultHomeFeeds>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>(), LikePostingInterface {
    inner class ViewHolder(val binding: ItemRecyclerHomeFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultHomeFeeds) {
            // profilePicture
            binding.ivTopHomeFeedProfileImg.clipToOutline = true
            Glide.with(context).load(item.profilePicture).into(binding.ivTopHomeFeedProfileImg)
            // profileName
            binding.tvTopHomeFeedUserName.text = item.profileName
            // content
            val span = SpannableStringBuilder("${item.profileName} ${item.content}")
            span.setSpan(StyleSpan(Typeface.BOLD),0,item.profileName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.tvContentHomeFeedUserNameAndContent.text = span
            // place
            if(item.place != null) binding.tvTopHomeFeedPlace.text = item.place
            else binding.tvTopHomeFeedPlace.visibility = View.GONE

            // photo
            binding.viewPagerHomeFeed.apply {
                adapter = HomeFeedPagerAdapter(context, item.photos)
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            binding.indicatorPagerHomeFeed.setViewPager(binding.viewPagerHomeFeed)

            // likeCount
            binding.tvContentHomeFeedLike.text = "좋아요 " + item.likeCount +"개"
            var like = item.likeCount
            // likeOn
            binding.btnMidHomeFeedLike.isChecked = item.likeOn.on == 1
            binding.btnMidHomeFeedLike.setOnClickListener {
                when((it as AppCompatCheckBox).isChecked) {
                    true -> {
                        like++
                        LikePostingService(this@FeedAdapter).tryPostLikePosting(item.postId)
                    }
                    false -> {
                        like--
                        LikePostingService(this@FeedAdapter).tryPatchUnLikePosting(item.likeOn.id, item.likeOn.on)
                    }
                }
                binding.tvContentHomeFeedLike.text = "좋아요 " + like +"개"
            }

            // createdAt
            binding.tvHomeFeedPostDate.text = ElapsedTimeFunction().run {
                calculationTime(this.dateTimeToMillSec(item.createdAt))
            }

            // viewComment_click
            binding.tvContentHomeFeedCommentShow.setOnClickListener {
                val intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("postId", item.postId)
                intent.putExtra("profileName", item.profileName)
                intent.putExtra("profilePicture", item.profilePicture)
                intent.putExtra("createdAt", item.createdAt)
                intent.putExtra("content", item.content)
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemRecyclerHomeFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultHomeFeeds[position])
    }

    override fun getItemCount(): Int {
        return resultHomeFeeds.size
    }

    override fun onPostLikePostingSuccess(response: LikeCommentResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPostLikePostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignInError", message)
    }

    override fun onPatchUnLikePostingSuccess(response: LikeCommentResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPatchUnLikePostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignInError", message)
    }
}