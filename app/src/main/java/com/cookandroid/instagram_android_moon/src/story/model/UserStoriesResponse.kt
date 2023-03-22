package com.cookandroid.instagram_android_moon.src.story.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserStoriesResponse(
    @SerializedName("result") val result: MutableList<ResultUserStories>
) : BaseResponse()
