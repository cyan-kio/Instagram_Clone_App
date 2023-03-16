package com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction

import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse

interface FollowFunctionInterface {
    fun onPostFollowSuccess(response: FollowResponse)

    fun onPostFollowFailure(message: String)

    fun onPostUnFollowSuccess(response: FollowResponse)

    fun onPostUnFollowFailure(message: String)
}