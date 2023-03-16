package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class FollowersResponse(
    @SerializedName("result") val result: ResultFollowers
) : BaseResponse()
