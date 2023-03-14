package com.cookandroid.instagram_android_moon.src.main.follows.pager.followings

import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowingsRetrofitInterface {
    @GET("app/follows/followings")
    fun getFollowsFollowings(@Query("user-id") user_id: Int): Call<FollowingsResponse>
}