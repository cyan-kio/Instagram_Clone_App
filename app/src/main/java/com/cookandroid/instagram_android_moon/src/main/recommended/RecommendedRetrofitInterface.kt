package com.cookandroid.instagram_android_moon.src.main.recommended

import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.main.recommended.model.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendedRetrofitInterface {
    @GET("app/posts/recommended")
    fun getRecommended(): Call<RecommendedResponse>

    @GET("app/users")
    fun getUserResearch(@Query("user-keyword") user_keyword: String): Call<SearchUserResponse>
}