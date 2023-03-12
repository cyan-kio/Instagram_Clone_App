package com.cookandroid.instagram_android_moon.src.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentHomeBinding
import com.cookandroid.instagram_android_moon.src.main.home.adapter.HomeAdapter
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds
import kotlinx.coroutines.Runnable
import kotlin.concurrent.thread

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentInterface {
    private val resultHomeFeeds = mutableListOf<ResultHomeFeeds>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomeService(this).tryGetHomeFeeds()

        // Recycler_Home
//        binding.recyclerHome.apply {
//            adapter = HomeAdapter(context, resultHomeFeeds)
//            layoutManager =
//                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        }
    }

    override fun onGetHomeFeedsSuccess(response: HomeFeedsResponse) {
        binding.recyclerHome.apply {
            adapter = HomeAdapter(context, response.result)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
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
    }

    override fun onGetHomeFeedsFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}
