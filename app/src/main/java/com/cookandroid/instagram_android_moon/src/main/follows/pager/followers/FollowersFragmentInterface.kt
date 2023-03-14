package com.cookandroid.instagram_android_moon.src.main.follows.pager.followers

import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowersResponse

interface FollowersFragmentInterface {
    fun onGetFollowsFollowersSuccess(response: FollowersResponse)

    fun onGetFollowsFollowersFailure(message: String)
}