package com.cookandroid.instagram_android_moon.src.main.follows.pager.followings

import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowingsResponse

interface FollowingsFragmentInterface {

    fun onGetFollowsFollowingsSuccess(response: FollowingsResponse)

    fun onGetFollowsFollowingsFailure(message: String)
}