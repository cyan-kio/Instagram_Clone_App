package com.cookandroid.instagram_android_moon.src.main.home

import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import retrofit2.http.GET

interface HomeFragmentInterface {

    fun onGetHomeFeedsSuccess(response: HomeFeedsResponse)

    fun onGetHomeFeedsFailure(message: String)

    fun onGetHomeStoriesSuccess(response: HomeStoriesResponse)

    fun onGetHomeStoriesFailure(message: String)
}