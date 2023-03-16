package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentFollowingsBinding
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.adapter.FollowingsAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.model.FollowingsResponse

class FollowingsFragment : BaseFragment<FragmentFollowingsBinding>(FragmentFollowingsBinding::bind, R.layout.fragment_followings), FollowingsFragmentInterface {

    private lateinit var _userId : String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.LOGIN_USER_ID, null)
        if (userId != null) {
            _userId = userId
            FollowingsService(this).tryGetFollowings(userId.toInt())
        } else Log.d("GetProfileError", "user_id is null")
    }

    override fun onGetFollowingsSuccess(response: FollowingsResponse) {
        binding.recyclerFollowing.apply {
            adapter = FollowingsAdapter(context, response.result.followings, _userId)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        response.message?.let { showCustomToast(it) }
    }


    override fun onGetFollowingsFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }
}