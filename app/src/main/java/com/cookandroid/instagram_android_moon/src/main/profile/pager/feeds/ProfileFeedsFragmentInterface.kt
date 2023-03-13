package com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse

interface ProfileFeedsFragmentInterface {

    fun onGetProfileFeedsSuccess(response: ProfileFeedsResponse)

    fun onGetProfileFeedsFailure(message: String)
}