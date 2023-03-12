package com.cookandroid.instagram_android_moon.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentHomeBinding
import com.cookandroid.instagram_android_moon.src.main.home.adapter.FeedAdapter
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentInterface {
    private lateinit var feedAdapter : FeedAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val thread = Thread {
            HomeService(this).tryGetHomeFeeds()
        }
        thread.start()
        thread.join()

         // Recycler_Home
//        binding.recyclerHome.apply {
//            adapter = HomeAdapter(context, StoryAdapter(context), feedAdapter)
//            layoutManager =
//                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        }
//        binding.recyclerHomeStory.apply {
//            adapter = StoryAdapter(context)
//            layoutManager =
//                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
//        }
//        binding.scrollViewHome.isSmoothScrollingEnabled = true
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
            binding.recyclerHomeFeed.apply {
            adapter = FeedAdapter(context, response.result)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        response.message?.let { showCustomToast(it) }
        // recycler_home_feed

        //        val thread = Thread {
//            resultHomeFeeds.addAll(response.result)
//            Thread.sleep(2000)
//        }
//        thread.start()
//        try {
//            thread.join()
//        } catch (e:Exception) {
//            e.printStackTrace()
//        }
//        Log.d("CHECKONETWO", resultHomeFeeds[0].profileName)
//        response.message?.let { showCustomToast(it) }

//        binding.recyclerHomeFeed.apply {
//            adapter = FeedAdapter(context, response.result)
//            layoutManager =
//                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        }
    }

    override fun onGetHomeFeedsFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}
