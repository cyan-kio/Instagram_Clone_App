package com.cookandroid.instagram_android_moon.src.main.recommended.view

import com.cookandroid.instagram_android_moon.src.main.recommended.view.model.ViewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViewRetrofitInterface {
    @GET("app/posts/{post-id}")
    fun getView(@Path("post-id") postId : Int): Call<ViewResponse>
}