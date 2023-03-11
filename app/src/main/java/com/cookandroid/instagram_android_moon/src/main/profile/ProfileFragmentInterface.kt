package com.cookandroid.instagram_android_moon.src.main.profile

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse

interface ProfileFragmentInterface {

    fun onGetProfileSuccess(response: ProfileResponse)

    fun onGetProfileFailure(message: String)
}