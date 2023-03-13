package com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileFeedsRetrofitInterface {

    @GET("app/posts/profiles/user")
    fun getProfileFeeds(@Query("user-id") user_id: Int): Call<ProfileFeedsResponse>
}