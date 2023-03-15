package com.cookandroid.instagram_android_moon.src.post.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("result") val result: MutableList<ResultPost>
) : BaseResponse()
