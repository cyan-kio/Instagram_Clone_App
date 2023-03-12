package com.cookandroid.instagram_android_moon.src.main.profile

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileRetrofitInterface {
    @GET("app/users/{user-id}")
    fun getProfile(@Path("user-id") user_id: Int): Call<ProfileResponse>
}