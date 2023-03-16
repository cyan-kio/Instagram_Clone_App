package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers

import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.model.FollowersResponse

interface FollowersFragmentInterface {
    fun onGetFollowersSuccess(response: FollowersResponse)

    fun onGetFollowersFailure(message: String)
}