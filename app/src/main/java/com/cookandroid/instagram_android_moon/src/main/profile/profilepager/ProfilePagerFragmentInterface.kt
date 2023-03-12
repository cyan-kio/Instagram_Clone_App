package com.cookandroid.instagram_android_moon.src.main.profile.profilepager

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse

interface ProfilePagerFragmentInterface {

    fun onGetProfileFeedsSuccess(response: ProfileFeedsResponse)

    fun onGetProfileFeedsFailure(message: String)
}