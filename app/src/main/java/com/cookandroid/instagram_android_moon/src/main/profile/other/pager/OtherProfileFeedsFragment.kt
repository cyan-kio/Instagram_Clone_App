package com.cookandroid.instagram_android_moon.src.main.profile.other.pager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentOtherProfileFeedsBinding
import com.cookandroid.instagram_android_moon.src.main.profile.adapter.ProfileFeedsGridAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds.ProfileFeedsFragmentInterface
import com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds.ProfileFeedsService
import com.cookandroid.instagram_android_moon.util.GridSpaceItemDecoration

class OtherProfileFeedsFragment(private val userId: Int) : BaseFragment<FragmentOtherProfileFeedsBinding>(
    FragmentOtherProfileFeedsBinding::bind,
    R.layout.fragment_other_profile_feeds
), ProfileFeedsFragmentInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProfileFeedsService(this).tryGetProfileFeeds(user_id = userId)

    }

    override fun onGetProfileFeedsSuccess(response: ProfileFeedsResponse) {
        val resultProfileFeeds = response.result
        binding.recyclerOtherProfilePagerFeedsGrid.apply {
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