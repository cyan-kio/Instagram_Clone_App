package com.cookandroid.instagram_android_moon.src.main.recommended.view

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentViewBinding
import com.cookandroid.instagram_android_moon.src.comment.CommentActivity
import com.cookandroid.instagram_android_moon.src.main.home.adapter.HomeFeedPagerAdapter
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.LikePostingInterface
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.LikePostingService
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.ScrapPostingInterface
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.ScrapPostingService
import com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.model.ScrapPostingResponse
import com.cookandroid.instagram_android_moon.src.main.recommended.view.model.ViewResponse
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class ViewFragment(private val postId: Int) : BaseFragment<FragmentViewBinding>(FragmentViewBinding::bind, R.layout.fragment_view), ViewFragmentInterface,
    LikePostingInterface, ScrapPostingInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewService(this).tryGetView(postId = postId)
    }

    override fun onGetViewSuccess(response: ViewResponse) {
        val item = response.result[0]
        // profilePicture
        binding.ivTopHomeFeedProfileImg.clipToOutline = true
        Glide.with(this).load(item.profilePicture).into(binding.ivTopHomeFeedProfileImg)
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
                        LikePostingService(this@ViewFragment).tryPostLikePosting(item.postId)
                    } else {
                        LikePostingService(this@ViewFragment).tryPatchEditLikePosting(
                            item.likeOn.id,
                            true
                        )
                    }
                }
                false -> {
                    like--
                    LikePostingService(this@ViewFragment).tryPatchEditLikePosting(
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
                        ScrapPostingService(this@ViewFragment).tryPostScrapPosting(item.postId)
                    } else {
                        ScrapPostingService(this@ViewFragment).tryPatchEditScrapPosting(
                            item.scrapOn.id,
                            true
                        )
                    }
                    // scrap_Bar
                    Glide.with(this@ViewFragment).load(item.photos[0].photoUrl).into(binding.ivScrapBarHomeFeedImage)
                    binding.constScrapBarHomeFeed.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.constScrapBarHomeFeed.visibility = View.GONE
                    }, 3000)
                }
                false -> ScrapPostingService(this@ViewFragment).tryPatchEditScrapPosting(
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
            val intent = Intent(requireContext(), CommentActivity::class.java)
            intent.putExtra("postId", item.postId)
            intent.putExtra("profileName", item.profileName)
            intent.putExtra("profilePicture", item.profilePicture)
            intent.putExtra("createdAt", item.createdAt)
            intent.putExtra("content", item.content)
            startActivity(intent)
        }
    }

    override fun onGetViewFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("UnScrapPostingError", message)
    }

    override fun onPostLikePostingSuccess(response: LikePostingResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPostLikePostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("UnLikePostingError", message)
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
        Log.d("UnLikePostingError", message)
    }

    override fun onPatchEditScrapPostingSuccess(response: ScrapPostingResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPatchEditScrapPostingFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("UnLikePostingError", message)
    }
}