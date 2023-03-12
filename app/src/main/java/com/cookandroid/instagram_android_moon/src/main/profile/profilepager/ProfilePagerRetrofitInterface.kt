package com.cookandroid.instagram_android_moon.src.main.profile.profilepager

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfilePagerRetrofitInterface {

    @GET("app/posts/profiles/user?user-id=")
    fun getProfileFeeds(@Query("user-id") user_id: Int): Call<ProfileFeedsResponse>
}