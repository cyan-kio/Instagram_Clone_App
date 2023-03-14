package com.cookandroid.instagram_android_moon.src.main.follows.pager.followings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentFollowingBinding
import com.cookandroid.instagram_android_moon.databinding.FragmentProfileBinding
import com.cookandroid.instagram_android_moon.src.main.follows.adapter.FollowsAdapter
import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowersResponse
import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowingsResponse
import com.cookandroid.instagram_android_moon.src.main.follows.pager.followers.FollowersService

class FollowingFragment : BaseFragment<FragmentFollowingBinding>(FragmentFollowingBinding::bind, R.layout.fragment_following), FollowingsFragmentInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.LOGIN_USER_ID, null)
        if (userId != null) {
            FollowingsService(this).tryGetFollowsFollowings(userId.toInt())
        } else Log.d("GetProfileError", "user_id is null")
    }

    override fun onGetFollowsFollowingsSuccess(response: FollowingsResponse) {
        binding.recyclerFollowing.apply {
            adapter = FollowsAdapter(context, response.result.followings)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        response.message?.let { showCustomToast(it) }
    }


    override fun onGetFollowsFollowingsFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}