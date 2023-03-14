package com.cookandroid.instagram_android_moon.src.main.follows.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class FollowersResponse(
    @SerializedName("result") val result: ResultFollower
) : BaseResponse()
