package com.cookandroid.instagram_android_moon.src.main.follows.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class FollowingsResponse(
    @SerializedName("result") val result: ResultFollowing
) : BaseResponse()
