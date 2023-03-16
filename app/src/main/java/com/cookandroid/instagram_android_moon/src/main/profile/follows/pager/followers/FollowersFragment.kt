package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentFollowersBinding
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.adapter.FollowersAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.model.FollowersResponse

class FollowersFragment : BaseFragment<FragmentFollowersBinding>(FragmentFollowersBinding::bind, R.layout.fragment_followers),
    FollowersFragmentInterface {

    private lateinit var _userId : String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.LOGIN_USER_ID, null)
        if (userId != null) {
            _userId = userId
            FollowersService(this).tryGetFollowers(userId.toInt())
        } else Log.d("GetProfileError", "user_id is null")
    }


    override fun onGetFollowersSuccess(response: FollowersResponse) {
        binding.recyclerFollower.apply {
            adapter = FollowersAdapter(context, response.result.followers, _userId)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        response.message?.let { showCustomToast(it) }
    }

    override fun onGetFollowersFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }

}