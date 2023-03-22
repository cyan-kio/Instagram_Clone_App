package com.cookandroid.instagram_android_moon.src.story

import com.cookandroid.instagram_android_moon.src.signup.model.PostEmailSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.PostPhoneSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse
import com.cookandroid.instagram_android_moon.src.story.model.UserStoriesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StoryRetrofitInterface {
    @GET("app/stories")
    fun getUserStories(@Query ("user-id") user_id: Int): Call<UserStoriesResponse>
}