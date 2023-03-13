package com.cookandroid.instagram_android_moon.src.main.home.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class HomeStoriesResponse(
    @SerializedName("result") val result: MutableList<ResultHomeStories>
) : BaseResponse()
