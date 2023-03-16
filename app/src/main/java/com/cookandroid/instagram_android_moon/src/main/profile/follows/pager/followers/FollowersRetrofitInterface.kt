package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers

import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.model.FollowersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowersRetrofitInterface {
    @GET("app/follows/followers")
    fun getFollowers(@Query("user-id") user_id: Int): Call<FollowersResponse>
}