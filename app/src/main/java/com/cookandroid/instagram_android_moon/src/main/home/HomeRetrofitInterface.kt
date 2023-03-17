package com.cookandroid.instagram_android_moon.src.main.home

import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeRetrofitInterface {
    @GET("app/posts/followings")
    fun getHomeFeeds(): Call<HomeFeedsResponse>

    @GET("app/stories/followings")
    fun getHomeStories(): Call<HomeStoriesResponse>
}