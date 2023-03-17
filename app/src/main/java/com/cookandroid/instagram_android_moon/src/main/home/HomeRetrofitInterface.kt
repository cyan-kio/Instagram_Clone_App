package com.cookandroid.instagram_android_moon.src.main.home

import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeRetrofitInterface {
    @GET("app/posts/followings")
    fun getHomeFeeds(): Call<HomeFeedsResponse>

    @GET("app/stories/followings")
    fun getHomeStories(): Call<HomeStoriesResponse>
}