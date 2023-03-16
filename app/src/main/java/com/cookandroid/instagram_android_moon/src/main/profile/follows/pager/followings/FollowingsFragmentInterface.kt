package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings

import com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.model.FollowingsResponse

interface FollowingsFragmentInterface {

    fun onGetFollowingsSuccess(response: FollowingsResponse)

    fun onGetFollowingsFailure(message: String)
}