package com.cookandroid.instagram_android_moon.src.story

import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse
import com.cookandroid.instagram_android_moon.src.story.model.UserStoriesResponse

interface StoryActivityInterface {

    fun onGetUserStoriesSuccess(response: UserStoriesResponse)

    fun onGetUserStoriesFailure(message: String)
}