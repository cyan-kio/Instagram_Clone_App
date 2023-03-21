package com.cookandroid.instagram_android_moon.src.main.home.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
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
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.ScrapPostingInterface
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.ScrapPostingService
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.model.ScrapPostingResponse
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class FeedAdapter(val context: Context, private val resultHomeFeeds: MutableList<ResultHomeFeeds>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>(), LikePostingInterface, ScrapPostingInterface {
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
            span.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                item.profileName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.tvContentHomeFeedUserNameAndContent.text = span
            // place
            if (item.place != null) binding.tvTopHomeFeedPlace.text = item.place
            else binding.tvTopHomeFeedPlace.visibility = View.GONE

            // photo
            binding.viewPagerHomeFeed.apply {
                adapter = HomeFeedPagerAdapter(context, item.photos)
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            binding.indicatorPagerHomeFeed.setViewPager(binding.viewPagerHomeFeed)

            // likeCount
            binding.tvContentHomeFeedLike.text = "좋아요 " + item.likeCount + "개"
            var like = item.likeCount
            // likeOn
            if(item.likeOn.on == 1) binding.btnMidHomeFeedLike.isChecked = true
            binding.btnMidHomeFeedLike.setOnClickListener {
                when ((it as AppCompatCheckBox).isChecked) {
                    true -> {
                        like++
                        if(item.likeOn.id == 0) {
                            LikePostingService(this@FeedAdapter).tryPostLikePosting(item.postId)
                        } else {
                            LikePostingService(this@FeedAdapter).tryPatchEditLikePosting(
                                item.likeOn.id,
                                true
                            )
                        }
                    }
                    false -> {
                        like--
                        LikePostingService(this@FeedAdapter).tryPatchEditLikePosting(
                            item.likeOn.id,
                            false
                        )
                        Log.d("WHATISLIKEID", item.likeOn.id.toString())
                        Log.d("WHATISLIKEID", item.likeOn.on.toString())
                    }
                }
                binding.tvContentHomeFeedLike.text = "좋아요 " + like + "개"
            }

            // scrap
            if(item.scrapOn.on == 1) binding.btnMidHomeFeedScrap.isChecked = true
            binding.btnMidHomeFeedScrap.setOnClickListener {
                when ((it as AppCompatCheckBox).isChecked) {
                    true -> {
                        if(item.scrapOn.id == 0) {
                            ScrapPostingService(this@FeedAdapter).tryPostScrapPosting(item.postId)
                        } else {
                            ScrapPostingService(this@FeedAdapter).tryPatchEditScrapPosting(
                                item.scrapOn.id,
                                true
                            )
                        }
                        // scrap_Bar
                        Glide.with(context).load(item.photos[0].photoUrl).into(binding.ivScrapBarHomeFeedImage)
                        binding.constScrapBarHomeFeed.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.constScrapBarHomeFeed.visibility = View.GONE
                        }, 3000)
                    }
                    false -> ScrapPostingService(this@FeedAdapter).tryPatchEditScrapPosting(
                        item.scrapOn.id,
                        false
                    )
                }
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

    override fun onPostLikePostingSuccess(response: LikePostingResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPostLikePostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("LikePostingError", message)
    }

    override fun onPatchEditLikePostingSuccess(response: LikePostingResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPatchEditLikePostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("UnLikePostingError", message)
    }

    override fun onPostScrapPostingSuccess(response: ScrapPostingResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPostScrapPostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("ScrapPostingError", message)
    }

    override fun onPatchEditScrapPostingSuccess(response: ScrapPostingResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPatchEditScrapPostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("UnScrapPostingError", message)
    }
}