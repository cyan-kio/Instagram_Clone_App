package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings

import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.model.FollowingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowingsRetrofitInterface {
    @GET("app/follows/followings")
    fun getFollowings(@Query("user-id") user_id: Int): Call<FollowingsResponse>
}