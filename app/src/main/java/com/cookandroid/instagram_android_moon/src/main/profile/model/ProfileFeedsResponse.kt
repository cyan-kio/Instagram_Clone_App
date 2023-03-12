package com.cookandroid.instagram_android_moon.src.main.profile.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ProfileFeedsResponse(
    @SerializedName("result") val result: MutableList<ResultProfileFeeds>
) : BaseResponse()
