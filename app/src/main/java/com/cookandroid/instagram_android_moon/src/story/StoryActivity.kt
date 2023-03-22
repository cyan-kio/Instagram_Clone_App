package com.cookandroid.instagram_android_moon.src.story

import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityStoryBinding
import com.cookandroid.instagram_android_moon.src.story.model.UserStoriesResponse
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class StoryActivity : BaseActivity<ActivityStoryBinding>(ActivityStoryBinding::inflate), StoryActivityInterface {
    private lateinit var userIdcheck: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getIntExtra("user_id", -1)
        if(userId != -1) {
            StoryService(this).tryGetUserStories(userId)
            userIdcheck = userId.toString()
        }
    }

    inner class StoryThread: Thread() {
        override fun run() {
            super.run()
            startStory()
            sleep(6000)
            finish()
        }
    }

    private fun startStory() {
        binding.run {
                val currentProgress = prgStory.progress
                getInterval().subscribe {
                    prgStory.progress = currentProgress + it.toInt()
                }
        }
    }

    private fun getInterval(): Observable<Long> =
        Observable.interval(10L, TimeUnit.MILLISECONDS).map { interval ->
            interval + 1
        }.take(600)

    override fun onGetUserStoriesSuccess(response: UserStoriesResponse) {
        val resultUserStories = response.result[0]
        binding.apply {
            Glide.with(this@StoryActivity).load(resultUserStories.story_url).into(ivStoryImage)
            Glide.with(this@StoryActivity).load(resultUserStories.profile_image_url).into(ivStoryProfileImg)
            ivStoryProfileImg.clipToOutline = true
            tvStoryUserName.text = resultUserStories.nickname
            tvStoryPostDate.text = ElapsedTimeFunction().run {
                calculationTime(this.dateTimeToMillSec(resultUserStories.created_at))
            }
            if(resultUserStories.story_viewer_count != null) {
                constStoryMyStory.visibility = View.VISIBLE
                btnStoryMore.visibility = View.INVISIBLE
                if(resultUserStories.story_viewer_count == 1) {
                    Glide.with(this@StoryActivity).load(resultUserStories.story_viewer_profile_image_urls[0]).into(ivViewerMyStoryProfileImageOne)
                    ivViewerMyStoryProfileImageOne.clipToOutline = true
                    linearViewerOne.visibility = View.VISIBLE
                } else if(resultUserStories.story_viewer_count > 1) {
                    Glide.with(this@StoryActivity).load(resultUserStories.story_viewer_profile_image_urls[0]).into(ivViewerMyStoryProfileImageOne)
                    ivViewerMyStoryProfileImageOne.clipToOutline = true
                    linearViewerOne.visibility = View.VISIBLE
                    Glide.with(this@StoryActivity).load(resultUserStories.story_viewer_profile_image_urls[1]).into(ivViewerMyStoryProfileImageTwo)
                    ivViewerMyStoryProfileImageTwo.clipToOutline = true
                    linearViewerTwo.visibility = View.VISIBLE
                }
            } else {
                constStorySendMessage.visibility = View.VISIBLE
            }
        }
        val storyThread = StoryThread()
        storyThread.start()
    }

    override fun onGetUserStoriesFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("SignInError", message)
    }
}