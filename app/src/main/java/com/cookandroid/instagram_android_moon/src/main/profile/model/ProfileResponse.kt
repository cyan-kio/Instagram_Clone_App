package com.cookandroid.instagram_android_moon.src.main.profile.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("result") val result: ResultProfile
) : BaseResponse()
