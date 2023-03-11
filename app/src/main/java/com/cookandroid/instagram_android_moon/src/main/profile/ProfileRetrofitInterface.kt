package com.cookandroid.instagram_android_moon.src.main.profile

import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileRetrofitInterface {
    @POST("app/users/:user-id")
    fun getProfile(@Path("user-id") user_id: Int): Call<ProfileResponse>
}