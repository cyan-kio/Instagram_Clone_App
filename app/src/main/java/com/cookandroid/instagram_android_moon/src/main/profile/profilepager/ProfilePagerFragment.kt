package com.cookandroid.instagram_android_moon.src.main.profile.profilepager

import android.os.Bundle
import android.view.View
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentProfilePagerBinding

class ProfilePagerFragment(private val fragNum: Int) : BaseFragment<FragmentProfilePagerBinding>(FragmentProfilePagerBinding::bind, R.layout.fragment_profile_pager) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(fragNum) {
            1 -> binding.linearProfilePagerAddPhoto.visibility = View.VISIBLE
            else -> binding.linearProfilePagerTaggedPhoto.visibility = View.VISIBLE
        }
    }
}