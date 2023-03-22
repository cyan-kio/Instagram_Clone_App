package com.cookandroid.instagram_android_moon.src.story

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.signup.model.PostEmailSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.PostPhoneSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse
import com.cookandroid.instagram_android_moon.src.story.model.UserStoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryService(val storyActivityInterface: StoryActivityInterface) {

    fun tryGetUserStories(user_id: Int) {
        val storyRetrofitInterface = ApplicationClass.sRetrofit.create(StoryRetrofitInterface::class.java)
        storyRetrofitInterface.getUserStories(user_id).enqueue(object:Callback<UserStoriesResponse>{
            override fun onResponse(
                call: Call<UserStoriesResponse>,
                response: Response<UserStoriesResponse>
            ) {
                storyActivityInterface.onGetUserStoriesSuccess(response.body() as UserStoriesResponse)
            }

            override fun onFailure(call: Call<UserStoriesResponse>, t: Throwable) {
                storyActivityInterface.onGetUserStoriesFailure(t.message ?: "통신 오류")
            }
        })
    }
}