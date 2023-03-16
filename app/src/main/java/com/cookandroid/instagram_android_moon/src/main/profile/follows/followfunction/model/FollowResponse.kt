package com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class FollowResponse(
    @SerializedName("result") val result: ResultFollow
) : BaseResponse()
