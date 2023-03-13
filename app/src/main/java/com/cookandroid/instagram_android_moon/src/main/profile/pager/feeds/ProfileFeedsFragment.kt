package com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentProfileFeedsBinding
import com.cookandroid.instagram_android_moon.src.main.profile.adapter.ProfileFeedsGridAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse
import com.cookandroid.instagram_android_moon.util.GridSpaceItemDecoration

class ProfileFeedsFragment() : BaseFragment<FragmentProfileFeedsBinding>(
    FragmentProfileFeedsBinding::bind,
    R.layout.fragment_profile_feeds
), ProfileFeedsFragmentInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.LOGIN_USER_ID, null)
        binding.linearProfilePagerFeedsAddFeed.visibility = View.GONE
        binding.recyclerProfilePagerFeedsGrid.visibility = View.VISIBLE
        if(userId != null) {
            ProfileFeedsService(this).tryGetProfileFeeds(user_id = userId.toInt())
            Log.d("FEEDS_CHECK_USER_ID", userId)
        }
        else Log.d("GetProfileError", "user_id is null")

    }

    override fun onGetProfileFeedsSuccess(response: ProfileFeedsResponse) {
        val resultProfileFeeds = response.result
        binding.recyclerProfilePagerFeedsGrid.apply {
            adapter = ProfileFeedsGridAdapter(requireContext(), resultProfileFeeds)
            layoutManager = GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL,false)
            this.run {
                addItemDecoration(GridSpaceItemDecoration(3,10))
            }
        }
        response.message?.let { showCustomToast(it) }
    }

    override fun onGetProfileFeedsFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }


}