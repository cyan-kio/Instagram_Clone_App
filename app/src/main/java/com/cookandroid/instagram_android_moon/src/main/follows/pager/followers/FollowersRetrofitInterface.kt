package com.cookandroid.instagram_android_moon.src.main.follows.pager.followers

import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowersRetrofitInterface {
    @GET("app/follows/followers")
    fun getFollowsFollowers(@Query("user-id") user_id: Int): Call<FollowersResponse>
}