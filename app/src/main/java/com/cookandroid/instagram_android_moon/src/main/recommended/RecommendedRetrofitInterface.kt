package com.cookandroid.instagram_android_moon.src.main.recommended

import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import retrofit2.Call
import retrofit2.http.GET

interface RecommendedRetrofitInterface {
    @GET("app/posts/recommended")
    fun getRecommended(): Call<RecommendedResponse>
}