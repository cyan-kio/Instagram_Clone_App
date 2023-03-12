package com.cookandroid.instagram_android_moon.src.main.home

import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import retrofit2.http.GET

interface HomeFragmentInterface {

    fun onGetHomeFeedsSuccess(response: HomeFeedsResponse)

    fun onGetHomeFeedsFailure(message: String)
}