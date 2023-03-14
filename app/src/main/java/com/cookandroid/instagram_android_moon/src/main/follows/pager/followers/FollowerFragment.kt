package com.cookandroid.instagram_android_moon.src.main.follows.pager.followers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentFollowerBinding
import com.cookandroid.instagram_android_moon.src.main.follows.adapter.FollowsAdapter
import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowersResponse
import com.cookandroid.instagram_android_moon.src.main.follows.pager.followings.FollowingFragment

class FollowerFragment : BaseFragment<FragmentFollowerBinding>(FragmentFollowerBinding::bind, R.layout.fragment_follower),
    FollowersFragmentInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.LOGIN_USER_ID, null)
        if (userId != null) {
            FollowersService(this).tryGetFollowsFollowers(userId.toInt())
        } else Log.d("GetProfileError", "user_id is null")
    }


    override fun onGetFollowsFollowersSuccess(response: FollowersResponse) {
        binding.recyclerFollower.apply {
            adapter = FollowsAdapter(context, response.result.followers)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        response.message?.let { showCustomToast(it) }
    }

    override fun onGetFollowsFollowersFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}