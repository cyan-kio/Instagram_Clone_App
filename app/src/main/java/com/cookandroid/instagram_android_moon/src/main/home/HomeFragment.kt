package com.cookandroid.instagram_android_moon.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentHomeBinding
import com.cookandroid.instagram_android_moon.src.main.home.adapter.FeedAdapter
import com.cookandroid.instagram_android_moon.src.main.home.adapter.StoryAdapter
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentInterface {
    private lateinit var feedAdapter : FeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeFeedsThread = Thread {
            HomeService(this).tryGetHomeFeeds()
        }
        val homeStoryThread = Thread {
            HomeService(this).tryGetHomeStories()
        }
        homeFeedsThread.start()
        homeStoryThread.start()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d("ThreadTest111", "4")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ThreadTest111", "4")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ThreadTest111", "4")
    }

    override fun onGetHomeFeedsSuccess(response: HomeFeedsResponse) {
        if(response.result != null) {
            binding.recyclerHomeFeed.apply {
                adapter = FeedAdapter(context, response.result)
                layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun onGetHomeFeedsFailure(message: String) {
        Log.d("ProfileError", message)
    }

    override fun onGetHomeStoriesSuccess(response: HomeStoriesResponse) {
        binding.recyclerHomeStory.apply {
            adapter = StoryAdapter(context, response.result)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onGetHomeStoriesFailure(message: String) {
        Log.d("ProfileError", message)
    }
}
