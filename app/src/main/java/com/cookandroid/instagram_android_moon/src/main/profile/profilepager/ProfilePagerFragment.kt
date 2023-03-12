package com.cookandroid.instagram_android_moon.src.main.profile.profilepager

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.ItemPagerProfileFeedsBinding
import com.cookandroid.instagram_android_moon.src.main.profile.adapter.ProfileFeedsGridAdapter
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse

class ProfilePagerFragment(private val fragNum: Int) : BaseFragment<ItemPagerProfileFeedsBinding>(
    ItemPagerProfileFeedsBinding::bind,
    R.layout.item_pager_profile_feeds
), ProfilePagerFragmentInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (fragNum) {
            1 -> {
                binding.linearProfilePagerAddPhoto.visibility = View.VISIBLE

            }
            else -> binding.linearProfilePagerTaggedPhoto.visibility = View.VISIBLE
        }

    }

    override fun onGetProfileFeedsSuccess(response: ProfileFeedsResponse) {
        if (fragNum == 1) {
            val resultProfileFeeds = response.result
            binding.recyclerProfilePagerGrid.apply {
                adapter = ProfileFeedsGridAdapter(requireContext(), resultProfileFeeds)
                layoutManager = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL,false)
            }
        }
    }

    override fun onGetProfileFeedsFailure(message: String) {
        TODO("Not yet implemented")
    }
}